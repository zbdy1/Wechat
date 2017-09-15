package cxg.weChat.model;

import java.util.HashMap;
import java.util.List;

public class UserListBean {
	private int total;
	private int count;
	private HashMap<String,List> data;
	private String next_openid;
	public List<String> getUserOpenIdList(){
		return data.get("openid");
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public HashMap<String, List> getData() {
		return data;
	}
	public void setData(HashMap<String, List> data) {
		this.data = data;
	}
	public String getNext_openid() {
		return next_openid;
	}
	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	
}
