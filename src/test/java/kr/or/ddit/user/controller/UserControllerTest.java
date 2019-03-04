package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserControllerTest extends WebTestConfig {

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
		ModelAndView mav = mvcResult.getModelAndView();
		
		ModelMap modelMap = mav.getModelMap();
		
		List<UserVo> userList = (List<UserVo>) modelMap.get("userList");
		int userCnt = (Integer) modelMap.get("userCnt");
		int page = (Integer) modelMap.get("page");
		int pageSize = (Integer) modelMap.get("pageSize");
		

		/***Then***/
		assertEquals("user/userPagingList", mav.getViewName());
		assertNotNull(userList);
		assertEquals(10, userList.size());
		assertTrue(userCnt > 100);
		assertEquals(1, page);
		assertEquals(10, pageSize);
		
		
	}

}