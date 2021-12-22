package com.eltov.air.core.util.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class MultipartFileUtil {
	
	private final FileUtil fileUtil;
	@Autowired
	public MultipartFileUtil(FileUtil fileUtil) {
		this.fileUtil = fileUtil;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(MultipartFileUtil.class);
	
	MultipartHttpServletRequest files;
	public MultipartHttpServletRequest getFiles() {return files;}
	public void setFiles(MultipartHttpServletRequest files) {this.files = files;}
	
	MultipartFile file;
	public MultipartFile getFile() {return file;}
	public void setFile(MultipartFile file) {this.file = file;}
	
	// Only One File Process
	public void fileUploadProcess(String filePath, FileDTO fileDto) {
		isValid(file);
		setFileDto(file, filePath, fileDto);
		fileSave(fileDto);
	}
	
	/////////////////////////////////////////////////////////////////////////////
	// 유효성 검사
	
	//logger.info("getPrograms => {}.", "Search Data : NAME - " + searchDto.getSearchName() + " / " + "EXT - " + searchDto.getSearchSect());
	private void isValid(MultipartFile file) {
		if(file.isEmpty()) 		{logger.warn("isValid => {}.", "파일이 없습니다. 브라우저를 확인 하세요.");}
		if(file.getSize()<0) 	{logger.warn("isValid => {}.", "파일 사이즈가 0 입니다. 파일을 확인 하세요.");}
//		if(file.getContentType()) 	{logger.warn("isValid => {}.", "파일 사이즈가 0 입니다. 파일을 확인 하세요.");}
	}
	
	private FileDTO setFileDto(MultipartFile file, String filePath, FileDTO fileDto) {
//		FileDTO fileDto = new FileDTO();
		fileDto.setFileRealname(file.getOriginalFilename());
		fileDto.setFilePath(filePath);
		fileDto.setFileName(makingFileName());
		System.out.println((int)file.getSize() + " <-int");
		System.out.println(file.getSize() + " <-filesize");
		fileDto.setFileSize((int)file.getSize());
		// 사이즈, 파일명, 확장자, 형식, 타입
		
		return fileDto;
	}
	
	private String makingFileName() {
//		UUID uuid = new UUID(null);
		return null;
	}
	
	// 파일 저장
	private void fileSave(FileDTO fileDto) {

	}
	
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
