package com.eltov.air.core.DTO;

import com.eltov.air.core.util.CommUtil;

public class SearchDTO {

	private String searchSect;
	private String searchName;
	
	public String getSearchSect() {
		return CommUtil.getChkNull(searchSect);
	}
	public String getSearchName() {
		return CommUtil.getChkNull(searchName);
	}
	
	public void setSearchSect(String searchSect) {
		this.searchSect = searchSect;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
}
