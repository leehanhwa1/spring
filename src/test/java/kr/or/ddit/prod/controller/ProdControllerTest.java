package kr.or.ddit.prod.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.test.WebTestConfig;

public class ProdControllerTest extends WebTestConfig {
	
	@Resource(name="prodService")
	private IProdService prodService;

	@Test
	public void testProdList() throws Exception {
		
		/***Given***/
		
		
		/***When***/
		MvcResult mvcResult   	= mockMvc.perform(get("/prodList").param("lgu", "P101")).andReturn();
		ModelAndView mav 	  	= mvcResult.getModelAndView();
		String viewName 	  	= mav.getViewName();
		List<ProdVo> prodList =  (List<ProdVo>) mav.getModel().get("prodList");
		

		/***Then***/
		assertEquals("prod/prodList", viewName);
		assertNotNull(prodList);
		
		
	}

}
