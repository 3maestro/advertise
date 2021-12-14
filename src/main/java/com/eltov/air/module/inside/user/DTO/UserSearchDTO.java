package com.eltov.air.module.inside.user.DTO;

import com.eltov.air.core.util.CommUtil;

public class UserSearchDTO {
	
	private String search_auth;
	private String search_name;
	
	public String getSearch_auth() {
		return CommUtil.getChkNull(search_auth);
	}
	public String getSearch_name() {
		return CommUtil.getChkNull(search_name);
	}
	public void setSearch_auth(String search_auth) {
		this.search_auth = search_auth;
	}
	public void setSearch_name(String search_name) {
		this.search_name = search_name;
	}

}
