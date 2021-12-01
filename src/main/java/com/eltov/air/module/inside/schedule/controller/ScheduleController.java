package com.eltov.air.module.inside.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@GetMapping("/frame")
	public String getFrameSch() {
	
		return "schedule/schedule_frame";
	}
	
	
	@GetMapping("/device")
	public String getDeviceSch() {
		return "schedule/schedule_device";
	}
	
}
