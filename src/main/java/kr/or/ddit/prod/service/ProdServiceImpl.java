package kr.or.ddit.prod.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.model.ProdVo;

@Service("prodService")
public class ProdServiceImpl implements IProdService {
	
	
	@Resource(name="prodDao")
	private IProdDao prodDao;
	
	public ProdServiceImpl(){
		//prodDao = new ProdDaoImpl();
	}
	
	/**
	 * Method : getProdByLgu
	 * 작성자 : SEM
	 * 변경이력 :
	 * @param lgu
	 * @return
	 * Method 설명 : 제품 카테고리 하위 제품 리스트 조회
	 */
	@Override
	public List<ProdVo> getProdByLgu(String lgu) {
		
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<ProdVo> prodList = prodDao.getProdByLgu(sqlSession , lgu);
		
		sqlSession.close();
		return prodList;
	}

}
