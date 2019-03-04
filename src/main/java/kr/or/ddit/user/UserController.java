package kr.or.ddit.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	* 작성자 : PC09
	* 변경이력 :
	* @return
	* Method 설명 :
	*/
	@RequestMapping("/userAllList")
	public String userAllList(Model model) {
		
		List<UserVo> userList = userService.getAllUser();
		
		model.addAttribute("userList", userList);
		
		return "user/userAllList";
	}
	
	
	
	@RequestMapping("/userPagingList")
	public String userPagingList(/*@RequestParam(name="page", defaultValue="1")int page,
								 @RequestParam(name="pageSize",defaultValue="10")int pageSize*/
								 PageVo pageVo, Model model) {
		
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
		
		model.addAllAttributes(resultMap);
		model.addAttribute("page" , pageVo.getPage());
		model.addAttribute("pageSize" , pageVo.getPageSize());
		
		return "user/userPagingList";
		
	}
	
	

}