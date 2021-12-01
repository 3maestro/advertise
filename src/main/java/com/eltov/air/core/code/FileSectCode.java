package com.eltov.air.core.code;

public enum FileSectCode {
	LOGO_FILE("LOGO"), 
	MAIN_FILE("MAIN"), 
	MENU_FILE("MENU");
	
	private final String code;
	
	FileSectCode(String code){
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
