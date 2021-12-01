package com.eltov.air.core.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/component")
public class UiComponentController {

	@GetMapping("/default")
	public String getEltovDefault() {
		return "../component/component_default";
	}
	
	@GetMapping("/list")
	public String getEltovList() {
		return "../component/component_list";
	}
	
	@GetMapping("/regist")
	public String getEltovRegist() {
		return "../component/component_regist";
	}
	
	@GetMapping("/udpate")
	public String getEltovUpdate() {
		return "../component/component_udpate";
	}
}
