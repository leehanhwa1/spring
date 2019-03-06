package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.util.model.PageVo;

@Service("lprodService")
public class LprodServiceImpl implements ILprodService {

	
	@Resource(name="lprodDao")
	private ILprodDao lprodDao;
	
	public LprodServiceImpl(){
	}
	
	/**
	 * Method : getAllLprod
	 * 작성자 : SEM
	 * 변경이력 :
	 * @return
	 * Method 설명 : 제품 카테고리 전체 조회
	 */
	@Override
	public List<LprodVo> getAllLprod() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<LprodVo> lprodList = lprodDao.getAllLprod(sqlSession);
		
		sqlSession.close();
		return lprodList;
		
	}
	
	/**
	 * Method : selectLprodPagingList
	 * 작성자 : SEM
	 * 변경이력 :
	 * @param pageVo
	 * @return
	 * Method 설명 : 제품 카테고리 페이징 조회
	 */
	@Override
	public Map<String, Object> selectLprodPagingList(PageVo pageVo) {
		
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lprodList", lprodDao.selectLprodPagingList(sqlSession , pageVo));
		resultMap.put("totalCnt", lprodDao.getLprodCnt(sqlSession));
		
		sqlSession.close();
		
		return resultMap;
	}

}
