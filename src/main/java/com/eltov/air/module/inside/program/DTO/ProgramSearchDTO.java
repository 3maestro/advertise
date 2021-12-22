package com.eltov.air.module.inside.program.DTO;

import java.sql.Timestamp;

import com.eltov.air.core.util.CommUtil;

public class ProgramSearchDTO {
	
	private Integer programId;
	private Integer brnId;
	private String programSect;
	private String programType;
	private String programName;
	private String programArgument;
	private String programWorkPath;
	private String programDesc;
	private String dbStatus;
	private Integer regId;
	private Integer updId;
	private Integer delId;
	private Timestamp regDate;
	private Timestamp updDate;
	private Timestamp delDate;
	
	private String useVer;
	private String lastVer;
	private String lastUpdater;
	
	public Integer getProgramId() {
		return CommUtil.getChkNull(programId);
	}
	public Integer getBrnId() {
		return CommUtil.getChkNull(brnId);
	}
	public String getProgramSect() {
		return CommUtil.getChkNull(programSect);
	}
	public String getProgramType() {
		return CommUtil.getChkNull(programType);
	}
	public String getProgramName() {
		return CommUtil.getChkNull(programName);
	}
	public String getProgramArgument() {
		return CommUtil.getChkNull(programArgument);
	}
	public String getProgramWorkPath() {
		return CommUtil.getChkNull(programWorkPath);
	}
	public String getProgramDesc() {
		return CommUtil.getChkNull(programDesc);
	}
	public String getDbStatus() {
		return CommUtil.getChkNull(dbStatus);
	}
	public Integer getRegId() {
		return CommUtil.getChkNull(regId);
	}
	public Integer getUpdId() {
		return CommUtil.getChkNull(updId);
	}
	public Integer getDelId() {
		return CommUtil.getChkNull(delId);
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public Timestamp getUpdDate() {
		return updDate;
	}
	public Timestamp getDelDate() {
		return delDate;
	}
	public String getUseVer() {
		return useVer;
	}
	public String getLastVer() {
		return lastVer;
	}
	public String getLastUpdater() {
		return lastUpdater;
	}
	
	public void setProgramId(Integer programId) {
		this.programId = programId;
	}
	public void setBrnId(Integer brnId) {
		this.brnId = brnId;
	}
	public void setProgramSect(String programSect) {
		this.programSect = programSect;
	}
	public void setProgramType(String programType) {
		this.programType = programType;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public void setProgramArgument(String programArgument) {
		this.programArgument = programArgument;
	}
	public void setProgramWorkPath(String programWorkPath) {
		this.programWorkPath = programWorkPath;
	}
	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}
	public void setDbStatus(String dbStatus) {
		this.dbStatus = dbStatus;
	}
	public void setRegId(Integer regId) {
		this.regId = regId;
	}
	public void setUpdId(Integer updId) {
		this.updId = updId;
	}
	public void setDelId(Integer delId) {
		this.delId = delId;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}
	public void setDelDate(Timestamp delDate) {
		this.delDate = delDate;
	}
	public void setUseVer(String useVer) {
		this.useVer = useVer;
	}
	public void setLastVer(String lastVer) {
		this.lastVer = lastVer;
	}
	public void setLastUpdater(String lastUpdater) {
		this.lastUpdater = lastUpdater;
	}

	public ProgramSearchDTO(){}

	
}
