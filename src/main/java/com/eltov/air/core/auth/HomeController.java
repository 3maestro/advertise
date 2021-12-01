package com.eltov.air.core.auth;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eltov.air.core.annotation.SessionUser;
import com.eltov.air.module.inside.system.user.DTO.UserDTO;

@Controller
public class HomeController {
	
	@RequestMapping(value= {"/", "/dologin"}) //index.jsp(로그인폼)을 응답하는 매핑
	public String getIndex(@SessionUser UserDTO userDto) { 

		if(userDto == null) {
			return "index";
		}else if(StringUtils.equals(userDto.getUser_auth(), "ADMIN")) {
			return "redirect:/company/list";
		}else if(StringUtils.equals(userDto.getUser_auth(), "MANAGER")) {
			return "redirect:/main/dashboard";
		}else {
			return "index";
		}
	}

}
