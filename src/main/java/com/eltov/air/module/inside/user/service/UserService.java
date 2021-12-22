package com.eltov.air.module.inside.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eltov.air.core.code.ExceptionCode;
import com.eltov.air.core.config.CommConfig;
import com.eltov.air.core.exception.ExceptionWrapper;
import com.eltov.air.core.util.Pagination;
import com.eltov.air.module.inside.user.DTO.UserDTO;
import com.eltov.air.module.inside.user.DTO.UserLogDTO;
import com.eltov.air.module.inside.user.DTO.UserPrincipalDTO;
import com.eltov.air.module.inside.user.DTO.UserSearchDTO;
import com.eltov.air.module.inside.user.mapper.UserMapper;
import com.eltov.air.module.inside.user.session.UserSession;

@Service
public class UserService implements UserDetailsService {
	
	private final CommConfig config;
	
	private final UserMapper userMapper;
	private final UserLogService userServiceLog;
	
	@Autowired
	public UserService(CommConfig config, UserMapper userMapper, UserLogService userServiceLog) {
		this.config = config;
		this.userMapper = userMapper;
		this.userServiceLog = userServiceLog;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(" <---");
		UserDTO user = userMapper.getUserByIdname(username);
		if(user == null) throw new UsernameNotFoundException(username); 
		return new UserSession(user);
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(new SimpleGrantedAuthority("ADMIN"));
//		
//		return new User(user.getUser_idname(), user.getUser_passwd(), authorities);
	}

	
	//tmp for test
	public UserDTO getUsersTmp(int id) {
		UserDTO user = userMapper.getUsersTmp(id);
		return user;
	}
	
	public List<UserDTO> getUsers(UserSearchDTO userSearchParam, Pagination pagination) {
		
		int userCnt = userMapper.getUserCnt(userSearchParam);
		pagination.setTotalCount(userCnt);
		List<UserDTO> userList = userMapper.getUsers(userSearchParam, pagination);
		return userList;
	}
	
	public void isUserIdDuplicate(String userIdname) throws ExceptionWrapper {
		
    	int userCnt = userMapper.getUserIdCheck(userIdname);
    	if(userCnt != 0) {
    		throw new ExceptionWrapper(ExceptionCode.E301_USER_ID_DUPLICATION, new Exception("아이디 중복"));
    	}
	}
	
	public void setUserRegist(UserDTO userDto, UserDTO ssUser) throws ExceptionWrapper {
		
        userDto.setReg_id(ssUser.getUser_id());
        userDto.setBrn_id(ssUser.getBrn_id());
        userDto.setUser_passwd(getPasswordEncryption(userDto.getUser_passwd()));
        
    	this.setUserInsertDB(userDto);
    	        
    	userServiceLog.registUserLog(new UserLogDTO(ssUser.getBrn_id(), userDto.getReg_id(), "", "", "USER", userDto.getUser_name(), userDto.getUser_id(), "INSERT", ssUser.getIp_address()));
	}
	
	public int setUserInsertDB(UserDTO userDto) throws ExceptionWrapper {
		int retCnt = userMapper.setUserInsert(userDto);
		if(retCnt != 1) {
			throw new ExceptionWrapper(ExceptionCode.E108_DB_ABNORMAL_INSERT, new Exception("User Insert Count : " + retCnt));
		}
		return retCnt;
	}

	public UserDTO getUserInfoByUserId(int userId) throws ExceptionWrapper{
		
		UserDTO user = userMapper.getUserInfoByUserId(userId);
		if(user == null) {
			throw new ExceptionWrapper(ExceptionCode.E104_NOTHING_SELECTED, new Exception("No User : " + userId));
		}
		return user;
	}
	
	public UserDTO getUserInfoByResId(int resId) throws ExceptionWrapper{
		
		UserDTO user = userMapper.getUserInfoByResId(resId);
		return user;
	}

	@Transactional
	public void setUserEditProcess(UserDTO userDto, UserDTO ssUser) throws ExceptionWrapper {
		
        userDto.setUpd_id(ssUser.getUser_id());
        
    	//1. 패스워드도 변경이 필요하면 변경(진짜 변경인지 확인)
    	String rawInputPwd = userDto.getUser_passwd();
    	if(!StringUtils.isBlank(rawInputPwd)) {
    		setUserPwdUpdate(rawInputPwd, userDto.getUser_id());
    	}
    	setUserInfoUpdate(userDto);
    	userServiceLog.registUserLog(new UserLogDTO(ssUser.getBrn_id(), userDto.getUpd_id(), "", "", "USER", userDto.getUser_name(), userDto.getUser_id(), "UPDATE", ssUser.getIp_address()));
		
	}
	
	private void setUserPwdUpdate(String rawInputPwd, int userId) throws ExceptionWrapper {
		
		String encInputPwd = getPasswordEncryption(rawInputPwd);
		
		UserDTO userInfo = getUserInfoByUserId(userId);
		String savedPwd = userInfo.getUser_passwd();

		if(!encInputPwd.equals(savedPwd)) {
			userMapper.setUserPwdChange(encInputPwd, userId);
		}
	}
	
	private int setUserInfoUpdate(UserDTO userDto) throws ExceptionWrapper {
		int retCnt = userMapper.setUserInfoEdit(userDto);
    	if(retCnt != 1) {
    		throw new ExceptionWrapper(ExceptionCode.E105_DB_UPDATE_FAIL, new Exception("User Info Update Fail : " + userDto));
    	}
    	return retCnt;
	}
	
	public void setUserDelete(UserDTO userDto, UserDTO ssUser) throws ExceptionWrapper {
		userDto.setDel_id(ssUser.getUser_id());
		setUserDeleteDB(userDto);
    	userServiceLog.registUserLog(new UserLogDTO(ssUser.getBrn_id(), userDto.getDel_id(), "", "", "USER", userDto.getUser_name(), userDto.getUser_id(), "DELETE", ssUser.getIp_address()));
        
	}

	private int setUserDeleteDB(UserDTO userDto) throws ExceptionWrapper {
		int retCnt = userMapper.setUserDelete(userDto);
    	if(retCnt != 1) {
    		throw new ExceptionWrapper(ExceptionCode.E106_DB_DELETE_FAIL, new Exception("User info Delete Fail : " + userDto));
    	}
    	return retCnt;
	}
	
	public void setRememberMeCookie(HttpServletResponse res, String idsaveYN, String userIdname) {
		Cookie cookie = new Cookie("ck_adminname", userIdname);
		if(idsaveYN.equals("Y")) {
			cookie.setMaxAge(config.getGlCookieTime());
			cookie.setPath("/");
			cookie.setHttpOnly(true);
			//cookie.setSecure(true);
		}else{
			cookie.setMaxAge(0);
			cookie.setPath("/");
		}
		res.addCookie(cookie);
	}
	
	public void setUpdateLoginDate(int userId) {
		userMapper.setUserLoginDate(userId);
	}
	
	private String getPasswordEncryption(String rawInputPwd) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		return bcrypt.encode(rawInputPwd);
	}



}


//private final UserMapper userMapper;
//private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//@Autowired
//public UserService(UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder){
//	this.userMapper = userMapper;
//	this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//}
//
//@Override
//public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//	System.out.println(id + " <-username");
//	
//	ArrayList<UserDTO> userAuthes = userMapper.findByUserId(id);
//	
//	if(userAuthes.size() == 0) {
//		throw new UsernameNotFoundException("User "+id+" Not Found!");
//	}
//	
//	return new UserPrincipalDTO(userAuthes); //UserDetails 클래스를 상속받은 UserPrincipalDTO 리턴한다.
//	
//}
//
//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
//public String registUser(UserDTO userDto) {
//	userDto.setUser_passwd(bCryptPasswordEncoder.encode(userDto.getUser_passwd()));
//	int flag = userMapper.userSave(userDto);		
//	if (flag > 0) {
//
//		int userIdName = userMapper.findUserNo(userDto.getUser_idname());
//		int authId = userMapper.findRoleNo(userDto.getAuth_code());
//
//		userMapper.userRoleSave(userIdName, authId);
//
//		return "success";
//	}	 	
//	return "fail";
//}