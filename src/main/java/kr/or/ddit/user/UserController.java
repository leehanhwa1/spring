package kr.or.ddit.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.model.PageVo;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Resource(name="userService")
	private IUserService userService;
	
	/**
	 * Method : userAllList
	 * 작성자 : jin
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 전체 리스트 조회
	 */
	@RequestMapping("/userAllList")
	public String userAllList(Model model){
		
		List<UserVo> userList = userService.getAllUser();
		model.addAttribute("userList", userList);
//		return "user/userAllList";
		return "userAllListTiles";
	}
	
	
	/**
	 * Method : userPagingList
	 * 작성자 : jin
	 * 변경이력 :
	 * @param pageVo
	 * @param model
	 * @return
	 * Method 설명 : 사용자 페이징 리스트 조회
	 */
	@RequestMapping("/userPagingList")
	public String userPagingList(PageVo pageVo, Model model){
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize", pageVo.getPageSize());
		model.addAttribute("page", pageVo.getPage());
		
		return "userPagingListTiles";
	}
	
	/**
	 * Method : userPagingListAjaxView
	 * 작성자 : jin
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 페이징 리스트 View
	 */
	@RequestMapping("userPagingListAjaxView")
	public String userPagingListAjaxView(){
		return "userPagingListAjaxTiles";
	}
	
	/**
	 * Method : userPagingListAjax
	 * 작성자 : jin
	 * 변경이력 :
	 * @param pageVo
	 * @param model
	 * @return
	 * Method 설명 : 사용자 페이지 리스트 ajax 요청 처리
	 */
	@RequestMapping("userPagingListAjax")
	public String userPagingListAjax(PageVo pageVo, Model model){
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize", pageVo.getPageSize());
		model.addAttribute("page", pageVo.getPage());
		//userList, userCnt, pageSize, page
		// { userList : [ {userId : 'brown', userNm : '브라운'}.....{userId : 'sally', userNm : '샐리'},]
		//   userCnt : "110", 
		//   pageSize : "10",
		//   page : 2
		// }
		
		return "jsonView";
	}
	
	@RequestMapping("userPagingListAjaxHtml")
	public String userPagingListAjaxHtml(PageVo pageVo, Model model){
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize", pageVo.getPageSize());
		model.addAttribute("page", pageVo.getPage());
		
		
		return "user/userPagingListAjaxHtml";
	}
	
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public String user(@RequestParam("userId") String userId, Model model) {
		
		UserVo userVo = userService.selectUser(userId);
		model.addAttribute("userVo", userVo);
		return "user/user";
	}
	
	@RequestMapping("/profileImg")
	public void profileImg(HttpServletRequest req, HttpServletResponse resp, 
							@RequestParam("userId") String userId) throws IOException {
		
		resp.setHeader("content-Disposition", "attachment; filename=profile.png"); 
		resp.setContentType("image");
		
		UserVo userVo = userService.selectUser(userId);
		
		//3-1.realFilename이 존재할 경우
		//3-1-1. 해당 경로의 파일을 FileInputStream으로 읽는다
		FileInputStream fis;
		if(userVo != null && userVo.getRealfilename() != null)
			fis = new FileInputStream(new File(userVo.getRealfilename()));
		
		//3-2.realFilename이 존재하지 않을 경우
		//3-2-1. /upload/noimg.png (application.getRealPath())
		else{
			ServletContext application = req.getServletContext();
			String noimgPath = application.getRealPath("/upload/noimg.png");
			fis = new FileInputStream(new File(noimgPath));
		}
		
		//4.FileInputStream을 response객체의 outputStream 객체에 write
		ServletOutputStream sos = resp.getOutputStream();
		byte[] buff = new byte[512];
		int len = 0;
		while ((len = fis.read(buff)) > -1) {
			sos.write(buff);
		}
		
		sos.close();
		fis.close();
	}
	
	/**
	 * Method : userFrom
	 * 작성자 : jin
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 등록폼 요청
	 */
	@RequestMapping(path="/userForm", method=RequestMethod.GET)
	public String userFrom() {
		return "user/userForm";
	}
	
	@RequestMapping(path="/userForm", method=RequestMethod.POST)
	public String userFrom(UserVo userVo, @RequestPart("profile") MultipartFile profile,
							HttpSession session, Model model) throws IllegalStateException, IOException {
		
		UserVo duplicateUserVo = userService.selectUser(userVo.getUserId());
		
	      //중복체크 통과(신규 등록)
		  if (duplicateUserVo == null) {
			  String filename = "";
			  String realFilename = "";
			  
			  
			  //사용자 프로파일을 업로드 한경우
			  if(profile.getSize() > 0) {
				  filename = profile.getOriginalFilename();
				  realFilename = "d:\\picture\\" + UUID.randomUUID().toString();
				  profile.transferTo(new File(realFilename));
			  }
			  
			  userVo.setFilename(filename);
			  userVo.setRealfilename(realFilename);
			  
			  //사용자 비밀번호 암호화 로직 
			  userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			  
			  int insertCnt = 0;
			  try {
				  insertCnt = userService.insertUser(userVo);
			  }catch (Exception e) {
				e.printStackTrace();
			  }
			  
			  //정상입력(성공)
			  if (insertCnt == 1) {
				  session.setAttribute("msg", "정상 등록 되었습니다.");
				  return "redirect:/user/userPagingList"; //contextPath 작업 필요
			  }else {
				  return "user/userForm";	//검증 필요
			  }
		  }else {
			  //req.setAttribute("msg", "중복체크에 실패 했습니다.");
		      //doGet(req, resp);
			  model.addAttribute("msg", "중복체크에 실패 했습니다");
			  return "user/userForm"; //contextPath 작업 필요
			  
		  }
	}
	
	
	@RequestMapping(path="/userModifyForm", method=RequestMethod.GET)
	public String userModifyForm(UserVo userVo, Model model){
		
		userVo = userService.selectUser(userVo.getUserId());
		model.addAttribute("userVo", userVo);
		
		return "user/userModifyForm";
	}
	
	@RequestMapping(path="/userModifyForm", method=RequestMethod.POST)
	public String userModifyForm(UserVo userVo, @RequestPart("profile") MultipartFile profile, 
									RedirectAttributes ra, HttpServletRequest req) throws IllegalStateException, IOException{
		 
		if(profile.getSize() > 0) {
			String filename = profile.getOriginalFilename();
			String realFilename = "d:\\picture\\" + UUID.randomUUID().toString();
			profile.transferTo(new File(realFilename));
			
			userVo.setFilename(filename);
			userVo.setRealfilename(realFilename);
			
			//비밀번호 수정 요청여부
			//사용자가 값을 입력하지 않은경우 => 기존비밀번호 유지
			
		}
		
		if(userVo.getPass().equals("")) {
			UserVo userVoforPass = userService.selectUser(userVo.getUserId());
			userVo.setPass(userVoforPass.getPass());
			
		}else { //사용자가 비밀번호를 신규 등록한 경우
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		}
		
		int updateCnt = 0;
		try {
			updateCnt = userService.updateUser(userVo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(updateCnt == 1) {
			//reDirect 파라미터를 보내는 방법
			//1.url로 작성
			// "redirect:/user/user?userId=" + userVo.getUserId();
			//2.model객체의 속성 : 저장된 속성을 자동으로 파라미터 변환
			//	model.addAttribute("userId", userVo.getUserId())
			//  return "redirect:/user/user";
			//3.RedirectAttributes(ra) 객체를 이용
			//	ra.addAttribute("userId", userVo.getUserId());
			//  return "redirect:/user/user";
			ra.addAttribute("userId", userVo.getUserId());
			ra.addFlashAttribute("msg", "정상적으로 수정되었습니다.");
			return "redirect:"+ req.getContextPath() + "/user/user";
			
		}else {
			return "/user/userModifyForm";
		}
		
	}
}


