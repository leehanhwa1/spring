package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

public interface IUserDao {
	
	/**
	 * Method : getAllUser
	 * 작성자 : SEM
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 조회
	 */
	List<UserVo> getAllUser();

	/**
	 * Method : selectUser
	 * 작성자 : SEM
	 * 변경이력 :
	 * @param string
	 * @return
	 * Method 설명 : 사용자 조회
	 */
	UserVo selectUser(String string);
	
	
	/**
	 * Method : selectUserPagingList
	 * 작성자 : SEM
	 * 변경이력 :
	 * @param pageVo
	 * @return
	 * Method 설명 : 사용자 페이징 리스트 조회
	 */
	List<UserVo> selectUserPagingList(PageVo pageVo);
	
	/**
	 * Method : getUserCnt
	 * 작성자 : SEM
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 수를 조회
	 */
	int getUserCnt();
	
	
	/**
	* Method : insertUser
	* 작성자 : PC09
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	*/
	int insertUser(UserVo userVo);
	
	
	/**
	* Method : deleteUser
	* 작성자 : PC09
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	int deleteUser(String userId);
	
	
	
	/**
	* Method : updateUser
	* 작성자 : PC09
	* 변경이력 :
	* @param sqlSession
	* @param userId
	* @return
	* Method 설명 : 사용자 수정
	*/
	int updateUser(UserVo uservo);
	
	
	
	
	int encryptPass(UserVo uservo);
}









