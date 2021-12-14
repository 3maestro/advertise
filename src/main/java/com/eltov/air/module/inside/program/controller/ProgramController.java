package com.eltov.air.module.inside.program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/program")
public class ProgramController {

	@GetMapping("/list")
	public String getProgramList() {
		return "program/program_list";
	}
	
	
}
