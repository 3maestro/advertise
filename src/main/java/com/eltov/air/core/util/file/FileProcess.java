package com.eltov.air.core.util.file;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eltov.air.core.code.ExceptionCode;
import com.eltov.air.core.config.CommConfig;
import com.eltov.air.core.exception.ExceptionWrapper;

@Component
public class FileProcess {
	
//	private final FileUtil fileUtil;
//	
//	@Autowired
//	public FileProcess(FileUtil fileUtil) {
//		this.fileUtil = fileUtil;
//	}

	private static final Logger logger = LoggerFactory.getLogger(MultipartFileUtil.class);
	
	MultipartHttpServletRequest files;
	public MultipartHttpServletRequest getFiles() {return files;}
	public void setFiles(MultipartHttpServletRequest files) {this.files = files;}
	
	MultipartFile file;
	public MultipartFile getFile() {return file;}
	public void setFile(MultipartFile file) {this.file = file;}
	
	// Only One File Process
	public void fileUploadProcess(String fileRootPath, String detailPath, FileDTO fileDto) {
		isValid(file);
		setFileDto(file, detailPath, fileDto);
		fileSave(fileRootPath, fileDto);
	}
	
	/////////////////////////////////////////////////////////////////////////////
	// 유효성 검사
	
	//logger.info("getPrograms => {}.", "Search Data : NAME - " + searchDto.getSearchName() + " / " + "EXT - " + searchDto.getSearchSect());
	private void isValid(MultipartFile file) {
		if(file == null || file.isEmpty()) 												{logger.warn("isValid => {}.", "파일이 없습니다. 브라우저를 확인 하세요.");}
		if(file.getSize()<=0) 															{logger.warn("isValid => {}.", "파일 사이즈가 0 입니다. 파일을 확인 하세요.");}
		if(file.getOriginalFilename() == null || file.getOriginalFilename().equals("")) {logger.warn("isValid => {}.", "파일명을 확인 하세요.");}
//		if(file.getContentType()) 	{logger.warn("isValid => {}.", "파일 사이즈가 0 입니다. 파일을 확인 하세요.");}
	}
	
	private FileDTO setFileDto(MultipartFile file, String detailPath, FileDTO fileDto) {
		fileDto.setFileRealname(file.getOriginalFilename());
		fileDto.setFilePath(detailPath);
		fileDto.setFileName(makingFileName(file.getOriginalFilename()));
		fileDto.setFileSize((int)file.getSize());
		fileDto.setFileExt(getFileExt(file.getOriginalFilename()));
		// 사이즈, 파일명, 확장자, 형식, 타입
		
		return fileDto;
	}
	
	private String makingFileName(String fileRealName) {
		String uuid = UUID.randomUUID().toString();
		Date today = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = format.format(today);
		// todo 예외처리
		String ext = getFileExt(fileRealName);
		String fileName = uuid + "_" + date + "." + ext;
		return fileName;
	}
	
	private String getFileExt(String fileRealName) {
		if(fileRealName.indexOf(".") > 0) {
			return fileRealName.substring(fileRealName.indexOf(".")+1);
		}else {
			logger.error("getFileExt => {}.", "The file extension is incorrect.");
			logger.error("getFileExt => {}.", "Incorrect file name is " + fileRealName);
			return "";
		}
	}
	
	// 파일 저장
	private void fileSave(String fileRootPath, FileDTO fileDto) {
		String fileDir = fileRootPath + fileDto.getFilePath();
		File uploadFolder = new File(fileDir);
		if (!uploadFolder.isDirectory()) {
			uploadFolder.mkdirs();
		}
		File targetFile = new File(fileDir, fileDto.getFileName());
		try {
			file.transferTo(targetFile);
		}catch(Exception e) {
			logger.error("fileSave => {}.", "Failed to save file.");
			logger.error("fileSave => {}.", "Failed file name is " + fileDto.getFileRealname());
			logger.error("fileSave => {}.", e.getMessage());
		}
	}
	
	public void fileDeleteProcess(String rootPath, String deletePath, String fileName) throws ExceptionWrapper {
		try {
			Path filePath = Paths.get(rootPath, deletePath, fileName);
			Files.delete(filePath);
		} catch (Exception e) {
			throw new ExceptionWrapper(ExceptionCode.E205_FILE_DELETE_FAIL, e);
		}
	}
	
//	public void setDelete(String uploadPath, String fileName) throws ExceptionWrapper {
//		try {
//			Path filePath = Paths.get(config.getGlPathFileRoot(), uploadPath, fileName);
//			Files.delete(filePath);
//		} catch (Exception e) {
//			throw new ExceptionWrapper(ExceptionCode.E205_FILE_DELETE_FAIL, e);
//		}
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	public static File setSaveFile(MultipartFile file, String absolutePath, String fileName) throws ExceptionWrapper {
//		File uploadFolder = new File(absolutePath);
//		if (!uploadFolder.isDirectory()) {
//			uploadFolder.mkdirs();
//		}
//
//		File targetFile = new File(absolutePath, fileName);
//		try {
//			file.transferTo(targetFile);
//		}catch(Exception e) {
//			e.printStackTrace();
//			throw new ExceptionWrapper(ExceptionCode.E202_FILE_SAVE_FAIL, e);
//		}
//		
//		return targetFile;
//	}
//	// Multi File Process
//	public void multiFileUploadProcess(String filePath) {
////		System.out.println(files.get(0).getOriginalFilename());
//		System.out.println(filePath + " <filePath");
//		
////		private Integer programId;
//		Iterator<String> fileNames = files.getFileNames();
//		while(fileNames.hasNext()) {
//			String fileName = fileNames.next();
//			System.out.println(fileName + " <-namemnandfkl");
//		}
//	}
}
