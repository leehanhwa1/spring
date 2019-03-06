package kr.or.ddit.lprod.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.test.WebTestConfig;

public class LprodControllerTest extends WebTestConfig {
	
	
	@Resource(name="lprodService")
	private ILprodService lprodService;
	

	@Test
	public void testLprodAllList() throws Exception {
		
		/***Given***/
		
		
		/***When***/
		MvcResult mvcResult   	= mockMvc.perform(get("/lprod/lprodList")).andReturn();
		ModelAndView mav 	  	= mvcResult.getModelAndView();
		String viewName 	  	= mav.getViewName();
		List<LprodVo> lprodList =  (List<LprodVo>) mav.getModel().get("lprodList");
		

		/***Then***/
		assertEquals("lprod/lprodList", viewName);
		assertNotNull(lprodList);
		
		
	}
	
	
	@Test
	public void testLprodPagingList() throws Exception {
		
		/***Given***/
		
		
		/***When***/
		MvcResult mvcResult   	= mockMvc.perform(get("/lprod/lprodPagingList")).andReturn();
		ModelAndView mav 	  	= mvcResult.getModelAndView();
		String viewName 	  	= mav.getViewName();
		
		ModelMap modelMap 		= mav.getModelMap();
		int pageSize	  		= (Integer)modelMap.get("pageSize");
		int page 		  		= (Integer)modelMap.get("page");
		int totalCnt 		  	= (Integer)modelMap.get("totalCnt");
		List<LprodVo> lprodList = (List<LprodVo>)modelMap.get("lprodList");
		

		/***Then***/
		assertEquals("lprod/lprodPagingList", viewName);
		assertNotNull(lprodList);
		assertEquals(10, lprodList.size());
		assertTrue(totalCnt > 10);
		assertEquals(1, page);
		assertEquals(10, pageSize);
		
		
	}

}