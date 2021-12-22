package com.eltov.air.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config")
public class CommConfig {
	
	private String glUrlRoot;
	private String glPathRoot;
	private String glPathFileRoot;
	private String glPathProgram;
	
	private int glSessionTime;
	private int glCookieTime;
	private int glFileLimit;
	private int glFileLimitImg;
	private int glFileLimitMov;
	
	private String glPathFfmpeg;
	private String glPathFfprobe;
	
	///////////////////////////////////////////////////////////////////////
	
	public String getGlUrlRoot() {
		return glUrlRoot;
	}
	public String getGlPathRoot() {
		return glPathRoot;
	}
	public String getGlPathFileRoot() {
		return glPathFileRoot;
	}
	public String getGlPathProgram() {
		return glPathProgram;
	}
	public int getGlSessionTime() {
		return glSessionTime;
	}
	public int getGlCookieTime() {
		return glCookieTime;
	}
	public int getGlFileLimit() {
		return glFileLimit;
	}
	public int getGlFileLimitImg() {
		return glFileLimitImg;
	}
	public int getGlFileLimitMov() {
		return glFileLimitMov;
	}
	public String getGlPathFfmpeg() {
		return glPathFfmpeg;
	}
	public String getGlPathFfprobe() {
		return glPathFfprobe;
	}

	///////////////////////////////////////////////////////////////////////
	
	public void setGlUrlRoot(String glUrlRoot) {
		this.glUrlRoot = glUrlRoot;
	}
	public void setGlPathRoot(String glPathRoot) {
		this.glPathRoot = glPathRoot;
	}
	public void setGlPathFileRoot(String glPathFileRoot) {
		this.glPathFileRoot = glPathFileRoot;
	}
	public void setGlPathProgram(String glPathProgram) {
		this.glPathProgram = glPathProgram;
	}
	public void setGlSessionTime(int glSessionTime) {
		this.glSessionTime = glSessionTime;
	}
	public void setGlCookieTime(int glCookieTime) {
		this.glCookieTime = glCookieTime;
	}
	public void setGlFileLimit(int glFileLimit) {
		this.glFileLimit = glFileLimit;
	}
	public void setGlFileLimitImg(int glFileLimitImg) {
		this.glFileLimitImg = glFileLimitImg;
	}
	public void setGlFileLimitMov(int glFileLimitMov) {
		this.glFileLimitMov = glFileLimitMov;
	}
	public void setGlPathFfmpeg(String glPathFfmpeg) {
		this.glPathFfmpeg = glPathFfmpeg;
	}
	public void setGlPathFfprobe(String glPathFfprobe) {
		this.glPathFfprobe = glPathFfprobe;
	}
//	private String gl_default_view_name;
//	private String gl_url_device_file;
//	private String gl_path_tempfile;
//	private int gl_file_limit;
//	private int gl_file_limit_img;
//	private int gl_file_limit_mov;
//	private String gl_conserver_ip;
//	private int gl_server_port01;
//	private int gl_server_port02;
//	private int gl_session_time;
//	private int gl_cookie_time;
//	private String gl_path_intro;
//	private String gl_path_info_origin;
//	private String gl_path_info_introduce;
//	private String gl_path_screen;
//	private String gl_path_restaurant;
//	private String gl_path_setting_map;
//	private String gl_path_setting_public;
//	private String gl_path_device_file;
//	private String gl_path_event;
//	private String gl_path_board;
//	private String gl_path_store;
//	private String gl_path_facility;
//	private String gl_path_error;
//	private String gl_path_ffmpeg;
//	private String gl_path_ffprobe;
//	private String gl_path_video;
//	private String gl_path_photo;
//	private String gl_path_batch_root;
//	private String gl_path_batch_tmp;
//	private String gl_path_batch_upload;
//	private String gl_path_building;
//	private String gl_path_park;
//	private String gl_path_quick;
//	private String gl_path_way_file;
	
	
	
}
