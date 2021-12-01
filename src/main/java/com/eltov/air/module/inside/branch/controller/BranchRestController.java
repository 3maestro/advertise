package com.eltov.air.module.inside.branch.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eltov.air.module.inside.branch.DTO.BranchDTO;

@RestController
@RequestMapping("/branch")
public class BranchRestController {

	private String uploadPath = "";
	
	@PostMapping("/regist")
	@ResponseBody
	public Map<String, Object> registBranch(BranchDTO branchDto, @RequestParam MultipartFile file) {

		System.out.println(branchDto.getBrnName() + " <-name");
		
		boolean result = false;
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			String fileName = generateFileName(file);
			File tmp = new File(uploadPath + fileName);
			
			resultMap.put("fileName", fileName);
			resultMap.put("fileSize", file.getSize());
			System.out.println("fileMap :" + resultMap);
			file.transferTo(tmp);
//				mapper.insertFiles(resultMap);
		} catch (Exception e) {
//				log.error("Error while uploading", e);
//				return false;
			System.out.println(e.getMessage());
		}
		
		if (!result) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}

		return resultMap;

	}

	private String generateFileName(MultipartFile multipartFile) {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String fileName = new SimpleDateFormat("yyyyMMdd").format(date) + "_" + multipartFile.getOriginalFilename();
		return fileName;
	}
}
