package com.eltov.air.module.inside.program.DTO;

import com.eltov.air.core.util.CommUtil;
import com.eltov.air.core.util.file.FileDTO;

public class ProgramFileDTO extends FileDTO {
	
	private Integer programId;
	private String fileVer;
	private String fileVerDesc;
	private String useYn;
	

	public Integer getProgramId() {
		return CommUtil.getChkNull(programId);
	}
	public String getFileVer() {
		return CommUtil.getChkNull(fileVer);
	}
	public String getFileVerDesc() {
		return CommUtil.getChkNull(fileVerDesc);
	}
	public String getUseYn() {
		return CommUtil.getChkNull(useYn);
	}
	
	public void setProgramId(Integer programId) {
		this.programId = programId;
	}
	public void setFileVer(String fileVer) {
		this.fileVer = fileVer;
	}
	public void setFileVerDesc(String fileVerDesc) {
		this.fileVerDesc = fileVerDesc;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
//	dto
//	
//	ProgramFileDTO(dto)
	
	
	////////////////////////////////////////////
//	private Integer fileId;
//	private Integer programId;
//	private String fileSect;
//	private String fileType;
//	private String fileName;
//	private String fileRealname;
//	private String fileVer;
//	private String fileVerDesc;
//	private Integer fileSize;
//	private String fileExt;
//	private String useYn;
//	private String filePath;
//	private String dbStatus;
//	private Timestamp regDate;
//	private Timestamp updDate;
//	private Timestamp delDate;

	/*
	public ProgramFileDTO(){}

	public Integer getFileId() {
		return CommUtil.getChkNull(fileId);
	}
	public Integer getProgramId() {
		return CommUtil.getChkNull(programId);
	}
	public String getFileSect() {
		return CommUtil.getChkNull(fileSect);
	}
	public String getFileType() {
		return CommUtil.getChkNull(fileType);
	}
	public String getFileName() {
		return CommUtil.getChkNull(fileName);
	}
	public String getFileRealname() {
		return CommUtil.getChkNull(fileRealname);
	}
	public String getFileVer() {
		return CommUtil.getChkNull(fileVer);
	}
	public String getFileVerDesc() {
		return CommUtil.getChkNull(fileVerDesc);
	}
	public Integer getFileSize() {
		return CommUtil.getChkNull(fileSize);
	}
	public String getFileExt() {
		return CommUtil.getChkNull(fileExt);
	}
	public String getUseYn() {
		return CommUtil.getChkNull(useYn);
	}
	public String getFilePath() {
		return CommUtil.getChkNull(filePath);
	}
	public String getDbStatus() {
		return CommUtil.getChkNull(dbStatus);
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
	
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public void setProgramId(Integer programId) {
		this.programId = programId;
	}
	public void setFileSect(String fileSect) {
		this.fileSect = fileSect;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setFileRealname(String fileRealname) {
		this.fileRealname = fileRealname;
	}
	public void setFileVer(String fileVer) {
		this.fileVer = fileVer;
	}
	public void setFileVerDesc(String fileVerDesc) {
		this.fileVerDesc = fileVerDesc;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public void setDbStatus(String dbStatus) {
		this.dbStatus = dbStatus;
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
*/
}
