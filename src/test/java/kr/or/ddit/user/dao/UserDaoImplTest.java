package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoImplTest extends LogicTestConfig {
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	// @Before -> @Test -> @After
	
	@Before
	public void setup(){
		
		userDao.deleteUser("test1");
	}
	

	/**
	 * Method : testGetAllUser
	 * 작성자 : SEM
	 * 변경이력 :
	 * Method 설명 : 전체 사용자 조회 테스트
	 */
	@Test
	public void testGetAllUser() {
		/***Given***/
		
		/***When***/
		List<UserVo> userList = userDao.getAllUser();
		for(UserVo userVo : userList)
			System.out.println(userVo);

		/***Then***/
		assertNotNull(userList);
		assertEquals(115, userList.size());
	}
	
	/**
	 * Method : testSelectUser
	 * 작성자 : SEM
	 * 변경이력 :
	 * Method 설명 : 사용자 조회 테스트
	 */
	@Test
	public void testSelectUser(){
		/***Given***/
		
		/***When***/
		UserVo userVo = userDao.selectUser("brown");

		/***Then***/
		assertEquals("brown", userVo.getUserId());
		assertEquals("testpass", userVo.getPass());
	}
	
	/**
	 * Method : testSelectUserPagingList
	 * 작성자 : SEM
	 * 변경이력 :
	 * Method 설명 : 사용자 페이징 리스트 조회 테스트
	 */
	@Test
	public void testSelectUserPagingList(){
		/***Given***/
		PageVo pageVo = new PageVo(1, 10);
		
		/***When***/
		List<UserVo> userList = userDao.selectUserPagingList(pageVo);
		for(UserVo user : userList)
			System.out.println(user);
		
		/***Then***/
		assertNotNull(userList);
		assertEquals(10, userList.size());
	}
	
	/**
	 * Method : testGetUserCnt
	 * 작성자 : SEM
	 * 변경이력 :
	 * Method 설명 : 사용자 수 조회 테스트
	 */
	@Test
	public void testGetUserCnt(){
		/***Given***/
		
		/***When***/
		int userCnt = userDao.getUserCnt();

		/***Then***/
		assertEquals(115, userCnt);
	}
	
	/**
	 * Method : testPagination
	 * 작성자 : SEM
	 * 변경이력 :
	 * Method 설명 :
	 */
	@Test
	public void testPagination(){
		/***Given***/
		int userCnt = 105;
		int pageSize = 10;
		
		/***When***/
		
		int lastPage = userCnt/pageSize + (userCnt%pageSize > 0 ? 1 : 0);
		
		/***Then***/
		assertEquals(11, lastPage);
	}
	
	@Test
	public void testPagination2(){
		/***Given***/
		int userCnt = 110;
		int pageSize = 10;
		
		/***When***/
		
		int lastPage = userCnt/pageSize + (userCnt%pageSize > 0 ? 1 : 0);
		
		/***Then***/
		assertEquals(11, lastPage);
	}
	
	@Test
	public void testInsertUser() {
		/***Given***/
		UserVo uservo = new UserVo();
		uservo.setUserId("test1");
		uservo.setUserNm("테스트");
		uservo.setAlias("곰");
		uservo.setAddr1("대전 중구 중앙로 76");
		uservo.setAddr2("2층 ddit");
		uservo.setZipcode("39462");
		uservo.setPass("brown1234");
		
		/***When***/
		int insertCount = userDao.insertUser(uservo);
		
		/***Then***/
		assertEquals(1, insertCount);
		
		
	}
	
	
	@Test
	public void testUpdateUser() {
		
		/***Given***/
		UserVo uservo = new UserVo();
		uservo.setUserId("moon");
		uservo.setAlias("123");
		uservo.setAddr1("서울특별시");
		uservo.setAddr2("강남구");
		uservo.setUserNm("안경");
		uservo.setZipcode("11111");
		uservo.setPass("qwerty");
		
		/***When***/
		int updateCount = userDao.updateUser(uservo);
		
		/***Then***/
		assertEquals(1, updateCount);
	}

}