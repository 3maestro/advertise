package com.eltov.air.module.inside.system.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.eltov.air.core.util.Pagination;
import com.eltov.air.module.inside.system.user.DTO.UserDTO;
import com.eltov.air.module.inside.system.user.DTO.UserSearchDTO;

@Mapper
public interface UserMapper {

	public Integer getUserIdCheck(String user_idname);
	public Integer setUserInsert(@Param("user")UserDTO model);
	public List<UserDTO> getUsers(@Param("sch")UserSearchDTO userSearchParam, @Param("page")Pagination page);
	public Integer getUserCnt(@Param("sch")UserSearchDTO userSearchParam);
	public UserDTO getUserInfoByUserId(@Param("user_id")int userId);
	public Integer setUserInfoEdit(@Param("user")UserDTO model);
	public Integer setUserPwdChange(@Param("pwd")String pwd, @Param("user_id")int userId);
	public Integer setUserDelete(@Param("user")UserDTO model);
	public UserDTO getUserByIdname(@Param("user_name")String userName);
	public Integer setUserLoginDate(@Param("user_id")int userId);
	
	public UserDTO getUserInfoByResId(@Param("res_id")int resId);
	
	public UserDTO getUsersTmp(@Param("user_id")int userId);
	
}
