package com.eltov.air.core.model;

import com.eltov.air.core.util.CommUtil;

public class ExcelTitleModel {
	
	public ExcelTitleModel() {}
	
	private String columnCode;
	private String columnName;
	private int columnSize;
	
	public ExcelTitleModel(String columnCode, String columnName, int columnSize) {
		super();
		this.columnCode = columnCode;
		this.columnName = columnName;
		this.columnSize = columnSize;
	}
	public String getColumnCode() {
		return CommUtil.getChkNull(columnCode);
	}
	public String getColumnName() {
		return CommUtil.getChkNull(columnName);
	}
	public int getColumnSize() {
		return CommUtil.getChkNull(columnSize);
	}
	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}
	
	
	
}
