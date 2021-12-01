package com.eltov.air.module.inside.system.user.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.eltov.air.core.annotation.SessionUser;
import com.eltov.air.core.exception.ExceptionWrapper;
import com.eltov.air.core.exception.ExceptionWrapperForAjax;
import com.eltov.air.core.model.ResponseModel;
import com.eltov.air.core.util.Pagination;
import com.eltov.air.module.inside.system.user.DTO.UserDTO;
import com.eltov.air.module.inside.system.user.DTO.UserLogDTO;
import com.eltov.air.module.inside.system.user.DTO.UserSearchDTO;
import com.eltov.air.module.inside.system.user.service.UserLogService;
import com.eltov.air.module.inside.system.user.service.UserService;

@Controller
@RequestMapping("/system/user")
// 초기화 되지 않은 모든 final 필드 또는 @NonNull로 마크돼있는 모든 필드에 대한 생성자를 자동으로 생성
//@RequiredArgsConstructor
public class UserController {
//	
//	private final SettingConfig settingConfig;
//	private final UserService userService;
//	private final UserLogService userLogService;
//	private final SessionLocaleResolver localeResolver;
//	private final MessageSource messageSource;
//	
//	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//	
//	@Autowired
//	public UserController(SettingConfig settingConfig, UserService userService, UserLogService userLogService,
//							SessionLocaleResolver localeResolver, MessageSource messageSource ) {
//		this.settingConfig = settingConfig;
//		this.userService = userService;
//		this.userLogService = userLogService;
//		this.localeResolver = localeResolver;
//		this.messageSource = messageSource;
//	}
//	
//	// tmp for test
////	@GetMapping(value = "/list2/{id}")
////	public ResponseEntity<Message> getUsers(@PathVariable int id) {
////		
////		try {
////			UserDTO user = userService.getUsersTmp(id);
////			System.out.println(user);
////			if(user == null) {
////				throw new CouponExpirationException();
////			}
////			Message message = new Message();
////			HttpHeaders headers= new HttpHeaders();
////			headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
////			
////			message.setStatus(UserErrorCode.OK);
////			message.setMessage("성공 코드");
////			message.setData(user);
////			return new ResponseEntity<>(message, headers, HttpStatus.OK);
////		}catch (Exception e) {
////			throw new CouponExpirationException();
////		}
////	}
//	
//	@GetMapping("/user_list")
//	public Model getUsers(UserSearchDTO userSearchParam, Pagination pagenation, Model model){
//		
//		List<UserDTO> userList;
//		
//		userList = userService.getUsers(userSearchParam, pagenation);
//		try {
//		}catch(Exception e) {
////			throw ExceptionWrapper.from(e);
//		}
//		
//		model.addAttribute("userList", userList);
//		model.addAttribute("page", pagenation);
//		model.addAttribute("AUTH", settingConfig.getGl_auth());
//		
//		return model;
//	}
//	
//	//관리자 가입 폼
//	@GetMapping("/write")
//	public void getUserWriteForm(HttpServletRequest request, Locale locale, Model model) {
//		model.addAttribute("AUTH", settingConfig.getGl_auth());
//	}
//	
//	//관리자 가입
//	@PostMapping("/write_action")
//	@ResponseBody
//	public ResponseEntity<ResponseModel> setUserWrite(UserDTO userDto,
//								   @SessionUser UserDTO user) throws ExceptionWrapperForAjax {
//		
//		try {
//			userService.setUserRegist(userDto, user);
//		}catch(Exception e) {
//			throw ExceptionWrapperForAjax.from(e);
//		}
//		
//		ResponseModel response = new ResponseModel("SUCC", "등록되었습니다.", "/stadmin/system/user/info?user_id=" + userDto.getUser_id());
//		
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//	
//	//아이디 중복체크
//	@GetMapping("/idcheck")
//	@ResponseBody
//	public ResponseEntity<ResponseModel> getUseIdCheck(@RequestParam(name="user_idname", defaultValue = "")String userIdname) throws ExceptionWrapperForAjax {
//		
//		try {
//			userService.isUserIdDuplicate(userIdname);
//		}catch(Exception e) {
//			throw ExceptionWrapperForAjax.from(e);
//		}
//		
//		ResponseModel response = new ResponseModel("SUCC", "사용할 수 있는 아이디입니다.");
//		
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//	
//	@GetMapping("/info")
//	public Model getUserInfo(@RequestParam(name="user_id", required = true)Integer userId, Model model) throws ExceptionWrapper {
//		
//		UserDTO user;
//		List<UserLogDTO> userLogList;
//		
//		try {
//			user = userService.getUserInfoByUserId(userId);
//			userLogList = userLogService.getLogListUser(userId);
//		}catch (Exception e) {
//			throw ExceptionWrapper.from(e);
//		}
//		
//		model.addAttribute("user", user);
//		model.addAttribute("userLogList", userLogList);
//		model.addAttribute("AUTH", settingConfig.getGl_auth());
//		model.addAttribute("LOG_CODE", settingConfig.getGl_log_code());
//		
//		return model;
//	}
//	
//	@PostMapping("/info_action")
//	@ResponseBody
//	public ResponseEntity<ResponseModel> setUserInfoEdit(UserDTO userDto,
//									  @SessionUser UserDTO user) throws ExceptionWrapperForAjax {
//		
//		try {
//			userService.setUserEditProcess(userDto, user);
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//			throw ExceptionWrapperForAjax.from(e);
//		}
//		
//		ResponseModel response = new ResponseModel("SUCC", "수정되었습니다.", "/stadmin/system/user/info?user_id="+userDto.getUser_id());
//		
//		return new ResponseEntity<>(response, HttpStatus.OK);
//		
//	}
//	
//	@PostMapping("/delete_action")
//	@ResponseBody
//	public ResponseEntity<ResponseModel> setUserDeleteAction(UserDTO userDto,
//										  @SessionUser UserDTO user) throws ExceptionWrapperForAjax {
//		
//		try {
//			userService.setUserDelete(userDto, user);
//		}catch(Exception e) {
//			throw ExceptionWrapperForAjax.from(e);
//		}
//		
//		ResponseModel response = new ResponseModel("SUCC", "삭제되었습니다.", "/stadmin/system/user/user_list");
//		
//		return new ResponseEntity<>(response, HttpStatus.OK);
//		
//	}
//	
//	@ExceptionHandler(MissingServletRequestParameterException.class)
//	public ModelAndView getParamException(MissingServletRequestParameterException e) {
//		
//		logger.error("ERROR::{} ", e.getMessage());
//		ModelAndView mav = new ModelAndView("/error");
//		
//		mav.addObject("errorCode", 400);
//		mav.addObject("errorMsg", "존재하지 않는 페이지이거나 유효하지 않은 요청입니다.");
//		
//		return mav;
//	}
//	
//	@ExceptionHandler(value = ExceptionWrapper.class)
//	public ModelAndView getException(ExceptionWrapper e) {
//		logger.error("ERR_POINT::{} ===> [{}] :: {} ", e.getErrorPoint(), e.getErrorCode(), e.getErrorCause());
//		
//		ModelAndView mav = new ModelAndView();
//		
//		ExceptionWrapper customException = e;
//		String msg = customException.getMessage();
//		
//		mav.setViewName("/error");
//		mav.addObject("msg", msg);
//		
//		return mav;
//	}
//	
//	@ExceptionHandler(value = ExceptionWrapperForAjax.class)
//	public ResponseEntity<ResponseModel> getExceptionForAjax(ExceptionWrapperForAjax e){
//		logger.error("ERR_POINT::{} ===> [{}] :: {} ", e.getErrorPoint(), e.getErrorCode(), e.getErrorCause());
//		
//		return new ResponseEntity<>(new ResponseModel("FAIL", e.getMessage(), ""), e.getHttpStatus());
//	}
	
//	@ExceptionHandler(value = CustomException.class)
//	public ResponseEntity<ErrorResponse> test(Exception e){
//		return  new ResponseEntity<>(new ErrorResponse();
//	}

}
