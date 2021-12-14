package com.eltov.air.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//@MapperScan(value= {"com.eltov.air.*"})
public class WebConfig {
	@Bean
	public BCryptPasswordEncoder passwordEnCoder() {
		return new BCryptPasswordEncoder();
	}
}
