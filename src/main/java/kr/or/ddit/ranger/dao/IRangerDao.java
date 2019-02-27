package kr.or.ddit.ranger.dao;

import java.util.List;

public interface IRangerDao {
	
	
	/**
	* Method : getRangers
	* 작성자 : PC09
	* 변경이력 :
	* @return
	* Method 설명 :
	*/
	List<String> getRangers();
	
	
	
	
	/**
	* Method : getRanger
	* 작성자 : PC09
	* 변경이력 :
	* @param listIndex
	* @return
	* Method 설명 : listIndex에 해당하는 레인저 이름을 반환
	*/
	String getRanger(int listIndex);
	

}
