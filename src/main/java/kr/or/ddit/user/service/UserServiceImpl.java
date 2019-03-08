package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource(name = "userDao")
	private IUserDao userDao;

	public UserServiceImpl() {
		
	}

	/**
	 * Method : getAllUser 작성자 : PC07 변경이력 :
	 * 
	 * @return Method 설명 : 전체 사용자 정보 조회
	 */
	@Override
	public List<UserVo> getAllUser() {

		List<UserVo> userList = userDao.getAllUser();

		return userList;
	}

	/**
	 * Method : selectUser 작성자 : PC07 변경이력 :
	 * 
	 * @param userId
	 * @return Method 설명 : 사용자 조회
	 */
	@Override
	public UserVo selectUser(String userId) {

		UserVo userVo = userDao.selectUser(userId);

		return userVo;
	}

	/**
	 * Method : selectUserPagingList 작성자 : PC07 변경이력 :
	 * 
	 * @param pageVo
	 * @return Method 설명 : 사용자 페이징 리스트 조회
	 */
	@Override
	public Map<String, Object> selectUserPagingList(PageVo pageVo) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("userList", userDao.selectUserPagingList(pageVo));
		resultMap.put("userCnt", userDao.getUserCnt());

		return resultMap;
	}

	@Override
	public int insertUser(UserVo userVo) {

		int cnt = userDao.insertUser(userVo);

		return cnt;
	}

	/**
	 * Method : deleteUser 작성자 : PC09 변경이력 :
	 * 
	 * @param userId
	 * @return Method 설명 : 사용자 삭제
	 */
	@Override
	public int deleteUser(String userId) {

		int cnt = userDao.deleteUser(userId);

		return cnt;
	}

	/**
	 * Method : updateUser 작성자 : PC09 변경이력 :
	 * 
	 * @param userId
	 * @return Method 설명 : 사용자 수정
	 */
	@Override
	public int updateUser(UserVo uservo) {

		int cnt = userDao.updateUser(uservo);

		return cnt;
	}

	@Override
	public int encryptPass() {

		String pass = null;

		List<UserVo> uservoList = userDao.getAllUser();
		int cnt = 0;
		for (int i = 0; i < uservoList.size(); i++) {
			pass = uservoList.get(i).getPass();
			String password = KISA_SHA256.encrypt("1234");
			UserVo uservo = uservoList.get(i);
			uservo.setPass(password);
			cnt += userDao.encryptPass(uservo);
		}

		return cnt;
	}

}