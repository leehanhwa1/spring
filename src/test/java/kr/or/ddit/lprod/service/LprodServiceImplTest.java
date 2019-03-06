package kr.or.ddit.lprod.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.util.model.PageVo;

public class LprodServiceImplTest extends LogicTestConfig {

	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	
	@Test
	public void testGetAllLprod() {
		
		/***Given***/
		
		
		/***When***/
		List<LprodVo> lprodList = lprodService.getAllLprod();
		

		/***Then***/
		assertNotNull(lprodList);
		
		
	}
	
	@Test
	public void testSelectLprodPagingList() {
		
		/***Given***/
		PageVo pageVo = new PageVo();
		
		/***When***/
		Map<String, Object> resultMap = lprodService.selectLprodPagingList(pageVo);

		/***Then***/
		assertNotNull(resultMap);
		
	}

}
