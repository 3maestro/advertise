package com.eltov.air.module.inside.device;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/device")
public class DeviceController {

	@GetMapping("/list")
	public String getDeviceList() {
		return "device/device_list";
	}
	
}
