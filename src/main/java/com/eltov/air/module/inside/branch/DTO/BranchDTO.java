package com.eltov.air.module.inside.branch.DTO;

import java.sql.Timestamp;

public class BranchDTO {

	private Integer brnId;
	private String brnSect;
	private String brnType;
	private String brnCode;
	private String brnName;
	private String brnPhone;
	private String brnDesc;
	private String dbStatus;
	private Integer regId;
	private Integer updId;
	private Integer delId;
	private Timestamp regDate;
	private Timestamp updDate;
	private Timestamp delDate;
	
	public Integer getBrnId() {
		return brnId;
	}
	public void setBrnId(Integer brnId) {
		this.brnId = brnId;
	}
	public String getBrnSect() {
		return brnSect;
	}
	public void setBrnSect(String brnSect) {
		this.brnSect = brnSect;
	}
	public String getBrnType() {
		return brnType;
	}
	public void setBrnType(String brnType) {
		this.brnType = brnType;
	}
	public String getBrnCode() {
		return brnCode;
	}
	public void setBrnCode(String brnCode) {
		this.brnCode = brnCode;
	}
	public String getBrnName() {
		return brnName;
	}
	public void setBrnName(String brnName) {
		this.brnName = brnName;
	}
	public String getBrnPhone() {
		return brnPhone;
	}
	public void setBrnPhone(String brnPhone) {
		this.brnPhone = brnPhone;
	}
	public String getBrnDesc() {
		return brnDesc;
	}
	public void setBrnDesc(String brnDesc) {
		this.brnDesc = brnDesc;
	}
	public String getDbStatus() {
		return dbStatus;
	}
	public void setDbStatus(String dbStatus) {
		this.dbStatus = dbStatus;
	}
	public Integer getRegId() {
		return regId;
	}
	public void setRegId(Integer regId) {
		this.regId = regId;
	}
	public Integer getUpdId() {
		return updId;
	}
	public void setUpdId(Integer updId) {
		this.updId = updId;
	}
	public Integer getDelId() {
		return delId;
	}
	public void setDelId(Integer delId) {
		this.delId = delId;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}
	public Timestamp getDelDate() {
		return delDate;
	}
	public void setDelDate(Timestamp delDate) {
		this.delDate = delDate;
	}
}
