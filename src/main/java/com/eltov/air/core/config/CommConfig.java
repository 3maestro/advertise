package com.eltov.air.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config")
public class CommConfig {
	
	private String gl_url_root;
	private String gl_url_root2;
	private String gl_path_root;
	private String gl_sms_api_token;
	private String gl_sms_api_smssend;
	private String gl_sms_api_id;
	private String gl_sms_api_pwd;
	private String gl_sms_calling_number;
	private String gl_sms_content;
	private String gl_default_view_name;
	private String gl_url_device_file;
	private String gl_path_tempfile;
	private int gl_file_limit;
	private int gl_file_limit_img;
	private int gl_file_limit_mov;
	private String gl_conserver_ip;
	private int gl_server_port01;
	private int gl_server_port02;
	private int gl_session_time;
	private int gl_cookie_time;
	private String gl_path_program;
	private String gl_path_intro;
	private String gl_path_info_origin;
	private String gl_path_info_introduce;
	private String gl_path_screen;
	private String gl_path_restaurant;
	private String gl_path_setting_map;
	private String gl_path_setting_public;
	private String gl_path_device_file;
	private String gl_path_event;
	private String gl_path_board;
	private String gl_path_store;
	private String gl_path_facility;
	private String gl_path_error;
	private String gl_path_ffmpeg;
	private String gl_path_ffprobe;
	private String gl_path_video;
	private String gl_path_photo;
	private String gl_path_batch_root;
	private String gl_path_batch_tmp;
	private String gl_path_batch_upload;
	private String gl_path_building;
	private String gl_path_park;
	private String gl_path_quick;
	private String gl_path_way_file;
	
	
	
	public String getGl_url_root() {
		return gl_url_root;
	}
	public String getGl_url_root2() {
		return gl_url_root2;
	}
	public String getGl_path_root() {
		return gl_path_root;
	}
	public String getGl_sms_api_token() {
		return gl_sms_api_token;
	}
	public String getGl_sms_api_smssend() {
		return gl_sms_api_smssend;
	}
	public String getGl_sms_api_id() {
		return gl_sms_api_id;
	}
	public String getGl_sms_api_pwd() {
		return gl_sms_api_pwd;
	}
	public String getGl_sms_calling_number() {
		return gl_sms_calling_number;
	}
	public String getGl_sms_content() {
		return gl_sms_content;
	}
	public String getGl_default_view_name() {
		return gl_default_view_name;
	}
	public String getGl_url_device_file() {
		return gl_url_device_file;
	}
	public String getGl_path_tempfile() {
		return gl_path_tempfile;
	}
	public int getGl_file_limit() {
		return gl_file_limit;
	}
	public int getGl_file_limit_img() {
		return gl_file_limit_img;
	}
	public int getGl_file_limit_mov() {
		return gl_file_limit_mov;
	}
	public String getGl_conserver_ip() {
		return gl_conserver_ip;
	}
	public int getGl_server_port01() {
		return gl_server_port01;
	}
	public int getGl_server_port02() {
		return gl_server_port02;
	}
	public int getGl_session_time() {
		return gl_session_time;
	}
	public int getGl_cookie_time() {
		return gl_cookie_time;
	}
	public String getGl_path_program() {
		return gl_path_program;
	}
	public String getGl_path_intro() {
		return gl_path_intro;
	}
	public String getGl_path_info_origin() {
		return gl_path_info_origin;
	}
	public String getGl_path_info_introduce() {
		return gl_path_info_introduce;
	}
	public String getGl_path_screen() {
		return gl_path_screen;
	}
	public String getGl_path_restaurant() {
		return gl_path_restaurant;
	}
	public String getGl_path_setting_map() {
		return gl_path_setting_map;
	}
	public String getGl_path_setting_public() {
		return gl_path_setting_public;
	}
	public String getGl_path_device_file() {
		return gl_path_device_file;
	}
	public String getGl_path_event() {
		return gl_path_event;
	}
	public String getGl_path_error() {
		return gl_path_error;
	}
	public String getGl_path_ffmpeg() {
		return gl_path_root + gl_path_ffmpeg;
	}
	public String getGl_path_ffprobe() {
		return gl_path_root + gl_path_ffprobe;
	}
	public String getGl_path_video() {
		return gl_path_video;
	}
	public String getGl_path_board() {
		return gl_path_board;
	}
	public String getGl_path_store() {
		return gl_path_store;
	}
	public String getGl_path_facility() {
		return gl_path_facility;
	}
	public String getGl_path_photo() {
		return gl_path_photo;
	}
	public String getGl_path_batch_root() {
		return gl_path_batch_root;
	}
	public String getGl_path_batch_tmp() {
		return gl_path_batch_tmp;
	}
	public String getGl_path_batch_upload() {
		return gl_path_batch_upload;
	}
	public String getGl_path_building() {
		return gl_path_building;
	}
	public String getGl_path_park() {
		return gl_path_park;
	}
	public String getGl_path_quick() {
		return gl_path_quick;
	}
	public String getGl_path_way_file() {
		return gl_path_way_file;
	}
	
	public void setGl_url_root(String gl_url_root) {
		this.gl_url_root = gl_url_root;
	}
	public void setGl_url_root2(String gl_url_root2) {
		this.gl_url_root2 = gl_url_root2;
	}
	public void setGl_path_root(String gl_path_root) {
		this.gl_path_root = gl_path_root;
	}
	public void setGl_sms_api_token(String gl_sms_api_token) {
		this.gl_sms_api_token = gl_sms_api_token;
	}
	public void setGl_sms_api_smssend(String gl_sms_api_smssend) {
		this.gl_sms_api_smssend = gl_sms_api_smssend;
	}
	public void setGl_sms_api_id(String gl_sms_api_id) {
		this.gl_sms_api_id = gl_sms_api_id;
	}
	public void setGl_sms_api_pwd(String gl_sms_api_pwd) {
		this.gl_sms_api_pwd = gl_sms_api_pwd;
	}
	public void setGl_sms_calling_number(String gl_sms_calling_number) {
		this.gl_sms_calling_number = gl_sms_calling_number;
	}
	public void setGl_sms_content(String gl_sms_content) {
		this.gl_sms_content = gl_sms_content;
	}
	public void setGl_default_view_name(String gl_default_view_name) {
		this.gl_default_view_name = gl_default_view_name;
	}
	public void setGl_url_device_file(String gl_url_device_file) {
		this.gl_url_device_file = gl_url_device_file;
	}
	public void setGl_path_tempfile(String gl_path_tempfile) {
		this.gl_path_tempfile = gl_path_tempfile;
	}
	public void setGl_file_limit(int gl_file_limit) {
		this.gl_file_limit = gl_file_limit;
	}
	public void setGl_file_limit_img(int gl_file_limit_img) {
		this.gl_file_limit_img = gl_file_limit_img;
	}
	public void setGl_file_limit_mov(int gl_file_limit_mov) {
		this.gl_file_limit_mov = gl_file_limit_mov;
	}
	public void setGl_conserver_ip(String gl_conserver_ip) {
		this.gl_conserver_ip = gl_conserver_ip;
	}
	public void setGl_server_port01(int gl_server_port01) {
		this.gl_server_port01 = gl_server_port01;
	}
	public void setGl_server_port02(int gl_server_port02) {
		this.gl_server_port02 = gl_server_port02;
	}
	public void setGl_session_time(int gl_session_time) {
		this.gl_session_time = gl_session_time;
	}
	public void setGl_cookie_time(int gl_cookie_time) {
		this.gl_cookie_time = gl_cookie_time;
	}
	public void setGl_path_program(String gl_path_program) {
		this.gl_path_program = gl_path_program;
	}
	public void setGl_path_intro(String gl_path_intro) {
		this.gl_path_intro = gl_path_intro;
	}
	public void setGl_path_info_origin(String gl_path_info_origin) {
		this.gl_path_info_origin = gl_path_info_origin;
	}
	public void setGl_path_info_introduce(String gl_path_info_introduce) {
		this.gl_path_info_introduce = gl_path_info_introduce;
	}
	public void setGl_path_screen(String gl_path_screen) {
		this.gl_path_screen = gl_path_screen;
	}
	public void setGl_path_restaurant(String gl_path_restaurant) {
		this.gl_path_restaurant = gl_path_restaurant;
	}
	public void setGl_path_setting_map(String gl_path_setting_map) {
		this.gl_path_setting_map = gl_path_setting_map;
	}
	public void setGl_path_setting_public(String gl_path_setting_public) {
		this.gl_path_setting_public = gl_path_setting_public;
	}
	public void setGl_path_device_file(String gl_path_device_file) {
		this.gl_path_device_file = gl_path_device_file;
	}
	public void setGl_path_event(String gl_path_event) {
		this.gl_path_event = gl_path_event;
	}
	public void setGl_path_error(String gl_path_error) {
		this.gl_path_error = gl_path_error;
	}
	public void setGl_path_ffmpeg(String gl_path_ffmpeg) {
		this.gl_path_ffmpeg = gl_path_ffmpeg;
	}
	public void setGl_path_ffprobe(String gl_path_ffprobe) {
		this.gl_path_ffprobe = gl_path_ffprobe;
	}
	public void setGl_path_video(String gl_path_video) {
		this.gl_path_video = gl_path_video;
	}
	public void setGl_path_board(String gl_path_board) {
		this.gl_path_board = gl_path_board;
	}
	public void setGl_path_store(String gl_path_store) {
		this.gl_path_store = gl_path_store;
	}
	public void setGl_path_facility(String gl_path_facility) {
		this.gl_path_facility = gl_path_facility;
	}
	public void setGl_path_photo(String gl_path_photo) {
		this.gl_path_photo = gl_path_photo;
	}
	public void setGl_path_batch_tmp(String gl_path_batch_tmp) {
		this.gl_path_batch_tmp = gl_path_batch_tmp;
	}
	public void setGl_path_batch_root(String gl_path_batch_root) {
		this.gl_path_batch_root = gl_path_batch_root;
	}
	public void setGl_path_batch_upload(String gl_path_batch_upload) {
		this.gl_path_batch_upload = gl_path_batch_upload;
	}
	public void setGl_path_building(String gl_path_building) {
		this.gl_path_building = gl_path_building;
	}
	public void setGl_path_park(String gl_path_park) {
		this.gl_path_park = gl_path_park;
	}
	public void setGl_path_quick(String gl_path_quick) {
		this.gl_path_quick = gl_path_quick;
	}
	public void setGl_path_way_file(String gl_path_way_file) {
		this.gl_path_way_file = gl_path_way_file;
	}
}
