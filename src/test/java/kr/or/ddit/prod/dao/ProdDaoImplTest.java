package kr.or.ddit.prod.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.test.LogicTestConfig;

public class ProdDaoImplTest extends LogicTestConfig {
	
	@Resource(name="prodDao")
	private IProdDao prodDao;
	private SqlSession sqlSession;
	
	
	@Before
	public void setup(){
		
		SqlSessionFactory sqlSessionFactory =  MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		
	}
	
	@After
	public void tearDown() {
		sqlSession.close();
	}
	

	@Test
	public void testGetProdByLgu() {
		
		/***Given***/
		String lgu = "P101";
		
		/***When***/
		List<ProdVo> prodList = prodDao.getProdByLgu(sqlSession, lgu);
		

		/***Then***/
		assertNotNull(prodList);
		
	}

}
