package com.eltov.air.module.inside.system.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.eltov.air.module.inside.system.user.DTO.UserLogDTO;

@Mapper
public interface UserLogMapper {

	public Integer registUserLog(@Param("log")UserLogDTO userLogModel);
	public List<UserLogDTO> getLogListUser(@Param("user_id")int userId);
}
