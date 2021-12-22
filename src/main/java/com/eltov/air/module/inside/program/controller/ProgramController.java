package com.eltov.air.module.inside.program.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eltov.air.core.DTO.SearchDTO;
import com.eltov.air.core.annotation.SessionUser;
import com.eltov.air.core.exception.ExceptionWrapper;
import com.eltov.air.module.inside.program.DTO.ProgramDTO;
import com.eltov.air.module.inside.program.service.ProgramService;
import com.eltov.air.module.inside.user.DTO.UserDTO;

@Controller
@RequestMapping("/program")
public class ProgramController {

	private final ProgramService programService;
	private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);
	
	@Autowired
	public ProgramController(ProgramService programService){
		this.programService = programService;
	}
	
	@GetMapping("/list")
	public String getPrograms(SearchDTO searchDto, @SessionUser UserDTO user, Model model) {
		
		List<ProgramDTO> programs = programService.getPrograms(searchDto, user);
		model.addAttribute("programs", programs);
		
		return "program/program_list";
	}
	
	@GetMapping(path="/download", params="format=file")
	public String programFileDownload(@RequestParam(name="downloadId", required = true)Integer fileId, UserDTO user, Model model) throws ExceptionWrapper {
		
		Map<String, Object> file;
		try {
			file = programService.programDownload(fileId, user);
		}catch(Exception e) {
			throw ExceptionWrapper.from(e);
		}
		
		model.addAllAttributes(file);
		
		return "FileDownloadView";
	}
}
