package kr.or.ddit.lprod.model;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LprodVo implements HttpSessionBindingListener {
	
	
	
	
	public LprodVo() {
		
		
	}


	public LprodVo(String lprod_id, String lprod_gu, String lprod_nm) {
		super();
		this.lprod_id = lprod_id;
		this.lprod_gu = lprod_gu;
		this.lprod_nm = lprod_nm;
	}


	private String lprod_id;
	private String lprod_gu;
	private String lprod_nm;
	
	public String getLprod_id() {
		return lprod_id;
	}


	public void setLprod_id(String lprod_id) {
		this.lprod_id = lprod_id;
	}


	public String getLprod_gu() {
		return lprod_gu;
	}


	public void setLprod_gu(String lprod_gu) {
		this.lprod_gu = lprod_gu;
	}


	public String getLprod_nm() {
		return lprod_nm;
	}


	public void setLprod_nm(String lprod_nm) {
		this.lprod_nm = lprod_nm;
	}


	@Override
	public String toString() {
		return "LprodVo [lprod_id=" + lprod_id + ", lprod_gu=" + lprod_gu
				+ ", lprod_nm=" + lprod_nm + "]";
	}


	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
