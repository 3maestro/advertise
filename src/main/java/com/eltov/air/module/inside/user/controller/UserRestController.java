package com.eltov.air.module.inside.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eltov.air.module.inside.user.DTO.UserDTO;
import com.eltov.air.module.inside.user.service.UserService;

@RestController
@RequestMapping("/system/user")
public class UserRestController {

	private UserService userService;
	
	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/save")
	public String saveUserInfo(@RequestBody UserDTO userDto) {
		System.out.println(userDto.getUser_idname() + " <-id");
		System.out.println(userDto.getUser_passwd() + " <-pw");
		return null;
	}
	
}
