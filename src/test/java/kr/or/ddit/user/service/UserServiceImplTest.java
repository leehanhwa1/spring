package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

import org.junit.Before;
import org.junit.Test;

public class UserServiceImplTest extends LogicTestConfig {

	@Resource(name="userService")
	private IUserService userService;
	
	@Before
	public void setup(){
		userService.deleteUser("test");
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
		List<UserVo> userList = userService.getAllUser();
		for(UserVo userVo : userList)
			System.out.println(userVo);

		/***Then***/
		assertNotNull(userList);
		assertEquals(113, userList.size());
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
		UserVo userVo = userService.selectUser("brown");

		/***Then***/
		assertEquals("brown", userVo.getUserId());
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
		Map<String, Object> resultMap =
				userService.selectUserPagingList(pageVo);
		
		List<UserVo> userList = (List<UserVo>)resultMap.get("userList");
		int userCnt = (Integer)resultMap.get("userCnt");
		
		for(UserVo user : userList)
			System.out.println(user);
		
		System.out.println("userCnt : " + userCnt);
		
		/***Then***/
		//userList
		assertNotNull(userList);
		assertEquals(10, userList.size());
		
		//userCnt
		assertEquals(113, userCnt);
	}
	
	
	@Test
	public void testInsertUser() {
		/***Given***/
		UserVo uservo = new UserVo();
		uservo.setUserId("test");
		uservo.setUserNm("테스트");
		uservo.setAlias("곰");
		uservo.setAddr1("대전 중구 중앙로 76");
		uservo.setAddr2("2층 ddit");
		uservo.setZipcode("39462");
		uservo.setPass("testpass");
		
		
		
		/***When***/
		int insertCount = userService.insertUser(uservo);
		
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
		int updateCount = userService.updateUser(uservo);
		
		/***Then***/
		assertEquals(1, updateCount);
	

	}
	
	
	//@Test
	public void testEncryptPass() {
		/***Given***/
		
		/***When***/
		userService.encryptPass();
		/***Then***/

	}

}





