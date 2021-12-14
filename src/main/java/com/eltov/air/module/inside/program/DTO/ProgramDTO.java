package com.eltov.air.module.inside.program.DTO;

import java.sql.Timestamp;

import com.eltov.air.core.util.CommUtil;

public class ProgramDTO {
	
	private Integer programId;
	private Integer brnId;
	private String programSect ;
	private String programType ;
	private String programName ;
	private String programArgument ;
	private String programWorkPath ;
	private String programDesc ;
	private String dbStatus ;
	private Integer regId ;
	private Integer updId ;
	private Integer delId ;
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
		return brnId;
	}
	public String getProgramSect() {
		return programSect;
	}
	public String getProgramType() {
		return programType;
	}
	public String getProgramName() {
		return programName;
	}
	public String getProgramArgument() {
		return programArgument;
	}
	public String getProgramWorkPath() {
		return programWorkPath;
	}
	public String getProgramDesc() {
		return programDesc;
	}
	public String getDbStatus() {
		return dbStatus;
	}
	public Integer getRegId() {
		return regId;
	}
	public Integer getUpdId() {
		return updId;
	}
	public Integer getDelId() {
		return delId;
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

	public ProgramDTO(){}

	
}
