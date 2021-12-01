package com.eltov.air.module.inside.system.user.DTO;

import java.sql.Timestamp;

import com.eltov.air.core.util.CommUtil;

public class UserLogDTO {
	
	private Integer log_id;
	private Integer brn_id;
	private Integer user_id;
	private String log_sect;
	private String log_type;
	private String log_code;
	private String log_msg;
	private Integer code_id;
	private String log_act;
	private String remote_ip;
	private Timestamp reg_date;
	private String past_time;
	
	private String brn_name;
	
	public UserLogDTO() {}
	
	public UserLogDTO(Integer brn_id, Integer user_id, String log_sect, String log_type, String log_code,
			String log_msg, Integer code_id, String log_act, String remote_ip) {
		super();
		this.brn_id = brn_id;
		this.user_id = user_id;
		this.log_sect = log_sect;
		this.log_type = log_type;
		this.log_code = log_code;
		this.log_msg = log_msg;
		this.code_id = code_id;
		this.log_act = log_act;
		this.remote_ip = remote_ip;
	}

	public Integer getLog_id() {
		return CommUtil.getChkNull(log_id);
	}
	public Integer getBrn_id() {
		return CommUtil.getChkNull(brn_id);
	}
	public Integer getUser_id() {
		return CommUtil.getChkNull(user_id);
	}
	public String getLog_sect() {
		return CommUtil.getChkNull(log_sect);
	}
	public String getLog_type() {
		return CommUtil.getChkNull(log_type);
	}
	public String getLog_code() {
		return CommUtil.getChkNull(log_code);
	}
	public String getLog_msg() {
		return CommUtil.getChkNull(log_msg);
	}
	public Integer getCode_id() {
		return CommUtil.getChkNull(code_id);
	}
	public String getLog_act() {
		return CommUtil.getChkNull(log_act);
	}
	public String getRemote_ip() {
		return CommUtil.getChkNull(remote_ip);
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public String getPast_time() {
		return past_time;
	}
	public String getBrn_name() {
		return brn_name;
	}
	public void setLog_id(Integer log_id) {
		this.log_id = log_id;
	}
	public void setBrn_id(Integer brn_id) {
		this.brn_id = brn_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public void setLog_sect(String log_sect) {
		this.log_sect = log_sect;
	}
	public void setLog_type(String log_type) {
		this.log_type = log_type;
	}
	public void setLog_code(String log_code) {
		this.log_code = log_code;
	}
	public void setLog_msg(String log_msg) {
		this.log_msg = log_msg;
	}
	public void setCode_id(Integer code_id) {
		this.code_id = code_id;
	}
	public void setLog_act(String log_act) {
		this.log_act = log_act;
	}
	public void setRemote_ip(String remote_ip) {
		this.remote_ip = remote_ip;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public void setPast_time(String past_time) {
		this.past_time = past_time;
	}
	public void setBrn_name(String brn_name) {
		this.brn_name = brn_name;
	}
}
