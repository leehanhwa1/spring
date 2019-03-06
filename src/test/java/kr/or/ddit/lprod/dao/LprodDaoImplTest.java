package kr.or.ddit.lprod.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.util.model.PageVo;

public class LprodDaoImplTest extends LogicTestConfig {
	
	private Logger logger = LoggerFactory.getLogger(LprodDaoImplTest.class);
	
	@Resource(name="lprodDao")
	private ILprodDao lprodDao;
	private SqlSession sqlSession;
	
	@Before
	public void setup(){
		//userDao = new UserDaoImpl();
		
		SqlSessionFactory sqlSessionFactory =  MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		
	}
	
	@After
	public void tearDown() {
		sqlSession.close();
	}

	@Test
	public void testgetAllLprod() {
		
		/***Given***/
		
		
		/***When***/
		List<LprodVo> lprodList = lprodDao.getAllLprod(sqlSession);
		for(LprodVo lprodVo : lprodList) {
			logger.debug("lprodVo : {}" , lprodVo);
		}
		sqlSession.close();
		

		/***Then***/
		assertNotNull(lprodList);
		
		
	}
	
	@Test
	public void testSelectLprodPagingList() {
		
		/***Given***/
		PageVo pageVo = new PageVo();
		
		/***When***/
		List<LprodVo> lprodList = lprodDao.selectLprodPagingList(sqlSession, pageVo);
		sqlSession.close();

		/***Then***/
		assertNotNull(lprodList);
		
		
		
	}
	
	
	@Test
	public void testgetLprodCnt() {
		
		/***Given***/
		
		
		/***When***/
		int getLprodCnt = lprodDao.getLprodCnt(sqlSession);
		sqlSession.close();
		

		/***Then***/
		assertEquals(20, getLprodCnt);
		
		
		
		
	}

}
