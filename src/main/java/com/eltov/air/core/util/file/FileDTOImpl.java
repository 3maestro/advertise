package com.eltov.air.core.util.file;

import java.sql.Timestamp;

import com.eltov.air.core.util.CommUtil;

public interface FileDTOImpl {
	public Integer getFileId();
	public String getFileSect();
	public String getFileType();
	public String getFileName();
	public String getFileRealname();
	public String getFileVer();
	public String getFileVerDesc();
	public Integer getFileSize();
	public String getFileExt();
	public String getFilePath();
	public String getDbStatus();
	public Timestamp getRegDate();
	public Timestamp getUpdDate();
	public Timestamp getDelDate();
	
	public void setFileId(Integer fileId);
	public void setFileSect(String fileSect);
	public void setFileType(String fileType);
	public void setFileName(String fileName);
	public void setFileRealname(String fileRealname);
	public void setFileVer(String fileVer);
	public void setFileVerDesc(String fileVerDesc);
	public void setFileSize(Integer fileSize);
	public void setFileExt(String fileExt);
	public void setFilePath(String filePath);
	public void setDbStatus(String dbStatus);
	public void setRegDate(Timestamp regDate);
	public void setUpdDate(Timestamp updDate);
	public void setDelDate(Timestamp delDate);
}
