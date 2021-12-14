package com.eltov.air.module.inside.program.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eltov.air.module.inside.program.DTO.ProgramDTO;
import com.eltov.air.module.inside.program.controller.ProgramController;
import com.eltov.air.module.inside.program.mapper.ProgramMapper;
import com.eltov.air.module.inside.user.DTO.UserDTO;

@Service
public class ProgramService {

	private final ProgramMapper programMapper;
	private static final Logger logger = LoggerFactory.getLogger(ProgramService.class);
	
	@Autowired
	public ProgramService(ProgramMapper programMapper) {
		this.programMapper = programMapper;
	}
	
	public void registProgram(MultipartHttpServletRequest multiPartRequest, ProgramDTO programDto, UserDTO user) {
		System.out.println(programDto.getProgramName());
		logger.info("registProgram info");
		logger.error("registProgram error");
		logger.warn("registProgram warn");
		registProgramDb(programDto);
		
		List<MultipartFile> fileList = multiPartRequest.getFiles("file");
		fileUploadProcess(fileList);
		
	}
	
	private void registProgramDb(ProgramDTO programDto) {
		programMapper.registProgramDb(programDto);
	}
	
	public void fileUploadProcess(List<MultipartFile> fileList) {
		System.out.println(fileList.size());
	}
	
}
