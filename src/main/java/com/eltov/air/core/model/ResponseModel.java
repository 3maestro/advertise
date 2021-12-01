package com.eltov.air.core.model;

import com.eltov.air.core.util.CommUtil;

public class ResponseModel<T> {

	private String code;
	private String msg;
	private String red;
	private T data;
	
	public ResponseModel() {}
	
//	public ResponseModel(String result) {
//		super();
//		String[] resultArr = result.split("|");
//		for(int i=0; resultArr.length < i; i++) {
//			resultArr[i];
//		}
//	}
	
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
	
	public ResponseModel(String code, String msg, String red, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.red = red;
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

	public void setData(T data) {
		this.data = data;
	}
	
}
