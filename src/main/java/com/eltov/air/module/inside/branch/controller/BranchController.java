package com.eltov.air.module.inside.branch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/branch")
public class BranchController {
	
	@GetMapping("/list")
	public String getList() {
		return "branch/branch_list";
	}
}
