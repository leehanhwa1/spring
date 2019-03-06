package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

public class UserControllerTest extends WebTestConfig {
	
	private static final String USER_INSERT_TEST_ID = "sallyTest2";
	   
	   @Resource(name="userService")
	   private IUserService userService;
	   
	   @Before
	   public void initData(){
	      userService.deleteUser(USER_INSERT_TEST_ID);
	   }

	/**
	* Method : testUserAllList
	* 작성자 : PC09
	* 변경이력 :
	* Method 설명 : 사용자 전체 조회 테스트
	 * @throws Exception 
	*/
	@Test
	public void testUserAllList() throws Exception {
		
		/***Given***/
		
		
		/***When***/
		MvcResult mvcResult   = mockMvc.perform(get("/user/userAllList")).andReturn();
		ModelAndView mav 	  = mvcResult.getModelAndView();
		String viewName 	  = mav.getViewName();
		List<UserVo> userList =  (List<UserVo>) mav.getModel().get("userList");

		/***Then***/
		assertEquals("user/userAllList", viewName);
		assertNotNull(userList);
		assertTrue(userList.size() > 100);
		
	}
	
	
	@Test
	public void testUserPagingList() throws Exception {
		
		/***Given***/
		MvcResult mvcResult   = mockMvc.perform(get("/user/userPagingList")).andReturn();
		
		
		/***When***/
		ModelAndView mav  = mvcResult.getModelAndView();
		
		ModelMap modelMap = mav.getModelMap();
		
		List<UserVo> userList = (List<UserVo>) modelMap.get("userList");
		int userCnt 		  = (Integer) modelMap.get("userCnt");
		int page 			  = (Integer) modelMap.get("page");
		int pageSize 		  = (Integer) modelMap.get("pageSize");
		

		/***Then***/
		assertEquals("user/userPagingList", mav.getViewName());
		assertNotNull(userList);
		assertEquals(10, userList.size());
		assertTrue(userCnt > 100);
		assertEquals(1, page);
		assertEquals(10, pageSize);
		
	}
	
	
	@Test
	public void testUser() throws Exception {
		/***Given***/
		
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", "brown")).andReturn();
		UserVo userVo 		= (UserVo) mvcResult.getModelAndView().getModel().get("userVo");
		String viewName 	= mvcResult.getModelAndView().getViewName();

		/***Then***/
		assertEquals("user/user", viewName);
		assertEquals("brown", userVo.getUserId());
	}
	
	
	@Test
	public void testUserForm() throws Exception {
		
		
		/***Given***/
		
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userForm")).andReturn();
		
		ModelAndView mav 	= mvcResult.getModelAndView();
		
		String viewName 	= mav.getViewName();

		/***Then***/
		assertEquals("user/userForm", viewName);
		
		
	}
	
	
	
	/**
	* Method : testUserForm_post
	* 작성자 : PC09
	* 변경이력 :
	* Method 설명 : 사용자 등록 요청 테스트
	*/
	@Test
	public void testUserForm_post_success() throws Exception {
		
		/***Given***/
		File profileFile 	   = new File("\\\\Sem-pc\\공유폴더\\Clap\\img\\userimg\\ryann.jpg");
		FileInputStream fis    = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "moong.png", "image/png", fis);
		
		MvcResult mvcResult    =
				mockMvc.perform(fileUpload("/user/userForm")
				.file(file)
				.param("userId", USER_INSERT_TEST_ID)
				.param("userNm", "샐리테스트")
				.param("alias", "병아리")
				.param("addr1", "대전시 중구 대흥로 76")
				.param("addr2", "2층 DDIT")
				.param("zipcode", "34942")
				.param("pass", "testpass"))
				.andExpect(view().name("redirect:/user/userPagingList"))
				.andReturn();
		
		/***When***/
		HttpSession session = mvcResult.getRequest().getSession();

		/***Then***/
		assertEquals("정상 등록되었습니다.", session.getAttribute("msg"));
		
		
	}
	
	
	
	/**
	* Method : testUserForm_post_fail_duplicateUser
	* 작성자 : PC09
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 등록 요청(중복 사용자로 인한 등록 실패 케이스) 테스트
	*/
	@Test
	public void testUserForm_post_fail_duplicateUser() throws Exception {
		
		/***Given***/
		File profileFile 	   = new File("\\\\Sem-pc\\공유폴더\\Clap\\img\\userimg\\ryann.jpg");
		FileInputStream fis    = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "moong.png", "image/png", fis);
		
		MvcResult mvcResult    =
				mockMvc.perform(fileUpload("/user/userForm")
				.file(file)
				.param("userId", "sally")
				.param("userNm", "샐리테스트")
				.param("alias", "병아리")
				.param("addr1", "대전시 중구 대흥로 76")
				.param("addr2", "2층 DDIT")
				.param("zipcode", "34942")
				.param("pass", "testpass"))
				.andExpect(view().name("user/userForm"))
				.andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String msg		 = (String)mav.getModel().get("msg");

		/***Then***/
		assertEquals("중복체크에 실패했습니다.", msg);
		
		
	}
	
	
	
	/**
	* Method : testUserForm_post_fail_insertError
	* 작성자 : PC09
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 등록(zipcode 사이즈 sql 에러) 테스트
	*/
	@Test
	public void testUserForm_post_fail_insertError() throws Exception {
		
		/***Given***/
		File profileFile 	   = new File("\\\\Sem-pc\\공유폴더\\Clap\\img\\userimg\\ryann.jpg");
		FileInputStream fis    = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "moong.png", "image/png", fis);
		
		MvcResult mvcResult    =
				mockMvc.perform(fileUpload("/user/userForm")
				.file(file)
				.param("userId", USER_INSERT_TEST_ID)
				.param("userNm", "샐리테스트")
				.param("alias", "병아리")
				.param("addr1", "대전시 중구 대흥로 76")
				.param("addr2", "2층 DDIT")
				.param("zipcode", "349423494234942")
				.param("pass", "testpass"))
				.andExpect(view().name("user/userForm"))
				.andReturn();
		
		/***When***/
		

		/***Then***/

		
		
	}
	
	
	@Test
	public void testUserModify_success() throws Exception {
		
		/***Given***/
		
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userModifyForm").param("userId", "brown")).andReturn();
		UserVo userVo		= (UserVo) mvcResult.getModelAndView().getModel().get("userVo");
		String viewName		= mvcResult.getModelAndView().getViewName();
		
		ModelAndView mav 	= mvcResult.getModelAndView();
		

		/***Then***/
		assertEquals("user/userModify", viewName);
		assertEquals("brown", userVo.getUserId());
		
		
	}
	
	
	@Test
	public void testUserModify_post_success() throws Exception {
		
		
		/***Given***/
		File profileFile 	   = new File("\\\\Sem-pc\\공유폴더\\Clap\\img\\userimg\\ryann.jpg");
		FileInputStream fis    = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "moong.png", "image/png", fis);
		
		MvcResult mvcResult    =
				mockMvc.perform(fileUpload("/user/userModifyForm")
				.file(file)
				.param("userId", "brown")
				.param("userNm", "샐리테스트")
				.param("alias", "병아리")
				.param("addr1", "대전시 중구 대흥로 76")
				.param("addr2", "2층 DDIT")
				.param("zipcode", "34942")
				.param("pass", "testpass"))
				.andExpect(view().name("redirect:/user/user?userId=brown"))
				.andReturn();
		
		/***When***/
		

		/***Then***/
	
		
	}
	
	
	@Test
	public void testUserModify_post_fail_updateError() throws Exception {
		
		
		/***Given***/
		File profileFile 	   = new File("\\\\Sem-pc\\공유폴더\\Clap\\img\\userimg\\ryann.jpg");
		FileInputStream fis    = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "moong.png", "image/png", fis);
		
		MvcResult mvcResult    =
				mockMvc.perform(fileUpload("/user/userModifyForm")
				.file(file)
				.param("userId", "brown")
				.param("userNm", "샐리테스트")
				.param("alias", "병아리")
				.param("addr1", "대전시 중구 대흥로 76")
				.param("addr2", "2층 DDIT")
				.param("zipcode", "3494285858585")
				.param("pass", "testpass"))
				.andExpect(view().name("user/userModifyForm"))
				.andReturn();
		
		/***When***/
		

		/***Then***/
		
	}
	
	
	
	
	//@Test
	//public void testProfileUpload() {
	

}