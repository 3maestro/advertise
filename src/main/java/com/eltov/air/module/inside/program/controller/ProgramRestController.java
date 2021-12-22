package com.eltov.air.module.inside.program.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eltov.air.core.annotation.SessionUser;
import com.eltov.air.core.exception.ExceptionWrapper;
import com.eltov.air.core.model.ResponseModel;
import com.eltov.air.core.util.file.FileProcess;
import com.eltov.air.module.inside.program.DTO.ProgramDTO;
import com.eltov.air.module.inside.program.DTO.ProgramFileDTO;
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
	
	@PostMapping("/regist")
	public ResponseEntity<ResponseModel> registProgram(FileProcess fileProcess, ProgramDTO programDto, UserDTO user) throws Exception {

		programService.registProgram(fileProcess, programDto, user);
		
		ResponseModel response = new ResponseModel("SUCC", "등록 되었습니다.", "RELOAD");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public static MultipartFile getFileFirstOne(Map<String, MultipartFile> fileMap) throws ExceptionWrapper {
		MultipartFile file = fileMap.values().stream().findFirst().get();
		return file;
	}
	
	@GetMapping("/update_view")
	public ResponseEntity<ResponseModel> updateProgramView(ProgramDTO programDto, @SessionUser UserDTO user){
		ProgramDTO program = programService.getProgramToUpdate(programDto, user);
		System.out.println(program);
		ResponseModel response = new ResponseModel("SUCC", "PROGRAM", "msg", program);
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	@PostMapping("/update")
	public ResponseEntity<ResponseModel> updateProgram(ProgramDTO programDto, UserDTO user) throws Exception {

		programService.updateProgram(programDto, user);
		
		ResponseModel response = new ResponseModel("SUCC", "수정 되었습니다.", "RELOAD");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/ver_update")
	public ResponseEntity<ResponseModel> updateProgramVer(FileProcess fileProcess, ProgramFileDTO programFileDto, UserDTO user) throws Exception {
		System.out.println(programFileDto.getProgramId() + " <-??????");
		programService.updateProgramVer(fileProcess, programFileDto, user);
		
		ResponseModel response = new ResponseModel("SUCC", "버전이 업데이트 되었습니다.", "RELOAD");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/change_ver")
	public ResponseEntity<ResponseModel> changeProgramVer(ProgramFileDTO programFileDto, @SessionUser UserDTO user){
		programService.changeProgramVer(programFileDto.getFileId(), programFileDto.getProgramId());
		ResponseModel response = new ResponseModel("SUCC", "해당 버전이 선택 되었습니다.", "RELOAD");
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	
	@PostMapping("/delete")
	public ResponseEntity<ResponseModel> deleteProgram(@RequestBody List<Integer> delIds, @SessionUser UserDTO user){
		programService.deleteProgram(delIds, user);
		ResponseModel response = new ResponseModel("SUCC", "선택된 프로그램이 삭제 되었습니다.", "RELOAD");
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
}
