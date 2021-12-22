package com.eltov.air.core.model;

import com.eltov.air.core.util.CommUtil;

public class ResponseModel<T> {

	private String code; 	// PROGRAM, CONTENTS, DEVICE ...
	private String msg; 	// String TYPE
	private String red;		// redirect url
	private String status;	// SUCC <- FAIL
	private T data;			// OBJECT TYPE
	
	public ResponseModel() {}
	
	public ResponseModel(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResponseModel(String code, String msg, String red) {
		super();
		this.code = code;
		this.msg = msg;
		this.red = red;
	}
	
	public ResponseModel(String status, String code, String msg, T data) {
		super();
		this.status = status;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public ResponseModel(String code, T data) {
		super();
		this.code = code;
		this.data = data;
	}

	public String getCode() {
		return CommUtil.getChkNull(code);
	}
	public String getMsg() {
		return CommUtil.getChkNull(msg);
	}
	public String getRed() {
		return CommUtil.getChkNull(red);
	}
	public String getStauts() {
		return CommUtil.getChkNull(status);
	}
	public T getData() {
		return data;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setRed(String red) {
		this.red = red;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setData(T data) {
		this.data = data;
	}
}
