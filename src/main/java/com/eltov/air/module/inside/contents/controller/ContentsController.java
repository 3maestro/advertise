package com.eltov.air.module.inside.contents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/contents")
public class ContentsController {

	@GetMapping("/upload")
	public String getUpload() {
		return "contents/contents_upload";
	}
	
//	@GetMapping("/modal")
//	public String getabcsd() {
//		return "contents/contents_modal";
//	}
	
	@GetMapping("/folder")
	public String getFolder() {
		return "contents/contents_folder";
	}
	
}
