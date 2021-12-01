package com.eltov.air.module.inside.system.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eltov.air.core.code.ExceptionCode;
import com.eltov.air.core.util.CommUtil;
import com.eltov.air.module.inside.system.user.DTO.UserLogDTO;
import com.eltov.air.module.inside.system.user.mapper.UserLogMapper;


@Service
public class UserLogService {
	
	private final UserLogMapper userLogMapper;
	private static final Logger logger = LoggerFactory.getLogger(UserLogService.class);
	
	@Autowired
	public UserLogService(UserLogMapper userLogMapper) {
		this.userLogMapper = userLogMapper;
	}
	
	
	public List<UserLogDTO> getLogListUser(int userId){
		List<UserLogDTO> userLogList = userLogMapper.getLogListUser(userId);
		
		for(UserLogDTO log : userLogList) {
			String pastTime = CommUtil.getDiffDayString(log.getReg_date().toString());
			log.setPast_time(pastTime);
		}
		
		return userLogList;
	}
	
	public void registUserLog(UserLogDTO userLogModel) {
		try {
			userLogMapper.registUserLog(userLogModel);
		}catch(Exception e) {
			logger.error("ERR_CODE: {} ===> Message: {} ", ExceptionCode.E103_LOG_INSERT_FAIL_DB ,e.getMessage());
		}
	}
}
