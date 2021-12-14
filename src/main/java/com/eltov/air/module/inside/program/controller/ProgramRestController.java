package com.eltov.air.module.inside.program.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eltov.air.core.exception.ExceptionWrapper;
import com.eltov.air.module.inside.program.DTO.ProgramDTO;
import com.eltov.air.module.inside.program.service.ProgramService;
import com.eltov.air.module.inside.user.DTO.UserDTO;

@RestController
@RequestMapping("/program")
public class ProgramRestController {

	private final ProgramService programService;
	private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);
	
	@Autowired
	public ProgramRestController(ProgramService programService){
		this.programService = programService;
	}
	
	@ResponseBody
	@PostMapping("/regist")
	public ResponseEntity<?> registProgram(MultipartHttpServletRequest multiPartRequest, ProgramDTO programDto, UserDTO user) {
//		List<MultipartFile> fileList = multiPartRequest.getFiles("file");
//		Map<String, MultipartFile> fileMap = multiPartRequest.getFileMap();
//		System.out.println(fileMap.values().stream().findFirst().get());
//		System.out.println(programDto.getProgramName() + " <-name");
		logger.info("Info {}.", programDto.getProgramName());
		logger.warn("Warn {}.", programDto.getProgramId());
		logger.error("Error {}.", user.getUser_idname());
		programService.registProgram(multiPartRequest, programDto, user);
		
//		for(MultipartFile file : fileList) {
//			System.out.println(file.getOriginalFilename());
//		}
//		MultipartFile file = FileUtil.getCheckNullFile(FileUtil.getFileFirstOne(fileMap));
//		Map<String, MultipartFile> fileMap = multiPartRequest.getFileMap();
		
		return null;
	}

	
	public static MultipartFile getFileFirstOne(Map<String, MultipartFile> fileMap) throws ExceptionWrapper {
		MultipartFile file = fileMap.values().stream().findFirst().get();
		return file;
	}
}
