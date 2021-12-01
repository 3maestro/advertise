package com.eltov.air.module.inside.company.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@GetMapping("/list")
	public String getCompanyList() {
		return "company/company_list";
	}
	
	@GetMapping("/dashboard")
	public String getDashboard(){
		return "company/company_dashboard"; 
	}
	
}
