package com.eltov.air.module.inside.user.DTO;

import java.io.Serializable;
import java.sql.Timestamp;

import com.eltov.air.core.util.CommUtil;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer user_id;
	private Integer brn_id;
	private String brn_name;
	private String brn_code;
	private Integer store_id;
	private String user_sect;
	private String user_type;
	private String user_idname;
	private String user_name;
	private String user_passwd;
	private String user_auth;
	private String user_phone;
	private String user_desc;
	private String db_status;
	private Integer reg_id;
	private Integer upd_id;
	private Integer del_id;
	private Timestamp reg_date;
	private Timestamp upd_date;
	private Timestamp del_date;
	private Timestamp login_date;
	private String pw_fst_status;
	private Timestamp pw_chg_date;
	private String last_updater;
	private String ip_address;
	
	public UserDTO() {}
	
	public Integer getUser_id() {
		return CommUtil.getChkNull(user_id);
	}
	public Integer getBrn_id() {
		return CommUtil.getChkNull(brn_id);
	}
	public String getBrn_name() {
		return CommUtil.getChkNull(brn_name);
	}
	public String getBrn_code() {
		return CommUtil.getChkNull(brn_code);
	}
	public Integer getStore_id() {
		return CommUtil.getChkNull(store_id);
	}
	public String getUser_sect() {
		return CommUtil.getChkNull(user_sect);
	}
	public String getUser_type() {
		return CommUtil.getChkNull(user_type);
	}
	public String getUser_idname() {
		return CommUtil.getChkNull(user_idname);
	}
	public String getUser_name() {
		return CommUtil.getChkNull(user_name);
	}
	public String getUser_passwd() {
		return CommUtil.getChkNull(user_passwd);
	}
	public String getUser_auth() {
		if(user_auth == null) user_auth = "NONE";
		return user_auth;
	}
	public String getUser_phone() {
		return CommUtil.getChkNull(user_phone);
	}
	public String getUser_desc() {
		return CommUtil.getChkNull(user_desc);
	}
	public String getDb_status() {
		return CommUtil.getChkNull(db_status);
	}
	public Integer getReg_id() {
		return CommUtil.getChkNull(reg_id);
	}
	public Integer getUpd_id() {
		return CommUtil.getChkNull(upd_id);
	}
	public Integer getDel_id() {
		return CommUtil.getChkNull(del_id);
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public Timestamp getUpd_date() {
		return upd_date;
	}
	public Timestamp getDel_date() {
		return del_date;
	}
	public Timestamp getLogin_date() {
		return login_date;
	}
	public String getPw_fst_status() {
		return pw_fst_status;
	}
	public Timestamp getPw_chg_date() {
		return pw_chg_date;
	}
	public String getLast_updater() {
		return CommUtil.getChkNull(last_updater);
	}
	public String getIp_address() {
		return CommUtil.getChkNull(ip_address);
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public void setBrn_id(Integer brn_id) {
		this.brn_id = brn_id;
	}
	public void setBrn_name(String brn_name) {
		this.brn_name = brn_name;
	}
	public void setBrn_code(String brn_code) {
		this.brn_code = brn_code;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public void setUser_sect(String user_sect) {
		this.user_sect = user_sect;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public void setUser_idname(String user_idname) {
		this.user_idname = user_idname;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}
	public void setUser_auth(String user_auth) {
		this.user_auth = user_auth;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public void setUser_desc(String user_desc) {
		this.user_desc = user_desc;
	}
	public void setDb_status(String db_status) {
		this.db_status = db_status;
	}
	public void setReg_id(Integer reg_id) {
		this.reg_id = reg_id;
	}
	public void setUpd_id(Integer upd_id) {
		this.upd_id = upd_id;
	}
	public void setDel_id(Integer del_id) {
		this.del_id = del_id;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public void setUpd_date(Timestamp upd_date) {
		this.upd_date = upd_date;
	}
	public void setDel_date(Timestamp del_date) {
		this.del_date = del_date;
	}
	public void setLogin_date(Timestamp login_date) {
		this.login_date = login_date;
	}
	public void setPw_fst_status(String pw_fst_status) {
		this.pw_fst_status = pw_fst_status;
	}
	public void setPw_chg_date(Timestamp pw_chg_date) {
		this.pw_chg_date = pw_chg_date;
	}
	public void setLast_updater(String last_updater) {
		this.last_updater = last_updater;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	
}
