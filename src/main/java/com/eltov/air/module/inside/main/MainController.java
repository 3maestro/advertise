package com.eltov.air.module.inside.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eltov.air.core.annotation.SessionUser;
import com.eltov.air.core.exception.ExceptionWrapper;
import com.eltov.air.core.exception.ExceptionWrapperForAjax;
import com.eltov.air.core.model.ResponseModel;
import com.eltov.air.core.util.CommUtil;
import com.eltov.air.module.inside.branch.DTO.BranchDTO;

@Controller
@RequestMapping("/main")
public class MainController {
	
//	private final StatsService statsService;
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

//	@Autowired
//	public MainController(StatsService statsService) {
//		this.statsService = statsService;
//	}
	
	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}
	
	//branch list를 보여주는 맵핑
	@GetMapping("/dashboard")
	public String getDashboard(BranchDTO branchDto) {
		System.out.println(branchDto.getBrnId() + " <-brnId");
		return "main/dashboard";
	}
	
	@GetMapping("/default")
	public String getDefault() {
		System.out.println("asdfasdf");
		return "../layout/default_content";
	}
	
//	@GetMapping("/main") //분석도구
//	public void getMain(Model model) {
//		String sday = LocalDate.now().minusMonths(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//		String eday = CommUtil.getToday("yyyy-MM-dd");
//		
//		model.addAttribute("SDAY", sday);
//		model.addAttribute("EDAY", eday);
//	}
//	
//	@GetMapping("/main_origin")
//	public void getMainOring() { //TODO : 제거
//	}

//	@GetMapping("/device_monitoring")
//	@ResponseBody
//	public ResponseEntity<ResponseModel> getDiviceData(@SessionUser UserDTO user) throws ExceptionWrapperForAjax {
//		Map<String, Object> kioskMonitoringData = null;
//		
//		try {
//			kioskMonitoringData = statsService.getKioskMonitoringData(user.getBrn_id());
//		}catch(Exception e) {
//			throw ExceptionWrapperForAjax.from(e);
//		}
//		
//		ResponseModel response = new ResponseModel("SUCC", "", "", kioskMonitoringData);
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//	
//	@GetMapping("/top_search_event")
//	@ResponseBody
//	public ResponseEntity<ResponseModel> getTopSearchEventData(@SessionUser UserDTO user) throws ExceptionWrapperForAjax {
//		
//		List<Map<String, Object>> topSearchEventData = null;
//		
//		try {
//			topSearchEventData = statsService.getTopSearchEventData(user.getBrn_id());
//		}catch(Exception e) {
//			throw ExceptionWrapperForAjax.from(e);
//		}
//		
//		ResponseModel response = new ResponseModel("SUCC", "", "", topSearchEventData);
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//	
//	@GetMapping("/top_search_store")
//	@ResponseBody
//	public ResponseEntity<ResponseModel> getTopSearchStoreData(@SessionUser UserDTO user) throws ExceptionWrapperForAjax {
//		
//		List<Map<String, Object>> topSearchStoretData = null;
//		
//		try {
//			topSearchStoretData = statsService.getTopSearchStoreData(user.getBrn_id());
//		}catch(Exception e) {
//			throw ExceptionWrapperForAjax.from(e);
//		}
//		
//		ResponseModel response = new ResponseModel("SUCC", "", "", topSearchStoretData);
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//	
//	@GetMapping("/kiosk_type_usage")
//	@ResponseBody
//	public ResponseEntity<ResponseModel> getKioskTypeUsageListData(@SessionUser UserDTO user) throws ExceptionWrapperForAjax {
//		
//		List<Map<String, Object>> kioskTypeUsageList = null;
//		
//		try {
//			kioskTypeUsageList = statsService.getKioskTypeUsage(user.getBrn_id());
//		}catch(Exception e) {
//			throw ExceptionWrapperForAjax.from(e);
//		}
//		
//		ResponseModel response = new ResponseModel("SUCC", "", "", kioskTypeUsageList);
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//	
//	@GetMapping("/top_touch_device_usage")
//	@ResponseBody
//	public ResponseEntity<ResponseModel> getTopTouchDeviceUsageData(@SessionUser UserDTO user) throws ExceptionWrapperForAjax {
//		
//		List<Map<String, Object>> topTouchDeviceUsageData = null;
//		
//		try {
//			topTouchDeviceUsageData = statsService.getTopTouchDeviceUsageData(user.getBrn_id());
//		}catch(Exception e) {
//			throw ExceptionWrapperForAjax.from(e);
//		}
//		
//		ResponseModel response = new ResponseModel("SUCC", "", "", topTouchDeviceUsageData);
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//	
//	@GetMapping("/category_usage")
//	@ResponseBody
//	public ResponseEntity<ResponseModel> getCategoryUsageData(@SessionUser UserDTO user) throws ExceptionWrapperForAjax {
//		
//		List<Map<String, Object>> categoryUsageData = null;
//		
//		try {
//			categoryUsageData = statsService.getCategoryUsageData(user.getBrn_id());
//		}catch(Exception e) {
//			throw ExceptionWrapperForAjax.from(e);
//		}
//		
//		ResponseModel response = new ResponseModel("SUCC", "", "", categoryUsageData);
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//	
//	@GetMapping("/top_menu")
//	@ResponseBody
//	public ResponseEntity<ResponseModel> getTopMenuListData(@SessionUser UserDTO user) throws ExceptionWrapperForAjax {
//		
//		List<Map<String, Object>> topMenuListData = null;
//		
//		try {
//			topMenuListData = statsService.getTopMenuListData(user.getBrn_id());
//		}catch(Exception e) {
//			throw ExceptionWrapperForAjax.from(e);
//		}
//		
//		ResponseModel response = new ResponseModel("SUCC", "", "", topMenuListData);
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
	
	@ExceptionHandler(value = ExceptionWrapper.class)
	public ModelAndView getException(ExceptionWrapper e) {
		ModelAndView mav = new ModelAndView();
		
		logger.error("ERR_POINT::{} ===> [{}] :: {} ", e.getErrorPoint(), e.getErrorCode(), e.getErrorCause());
		ExceptionWrapper customException = e;
		String msg = customException.getMessage();
		
		mav.setViewName("/error");
		mav.addObject("msg", msg);
		
		return mav;
	}
	
	@ExceptionHandler(value = ExceptionWrapperForAjax.class)
	public ResponseEntity<ResponseModel> getExceptionForAjax(ExceptionWrapperForAjax e){
		logger.error("ERR_POINT::{} ===> [{}] :: {} ", e.getErrorPoint(), e.getErrorCode(), e.getErrorCause());
		
		return new ResponseEntity<>(new ResponseModel("FAIL", e.getMessage(), ""), e.getHttpStatus());
	}
}
