package com.eltov.air.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.eltov.air.module.inside.user.service.UserService;

@Configuration
// Spring Security 설정할 클래스라고 정의
@EnableWebSecurity
// WebSebSecurityConfigurerAdapter 클래스를 상속받아 메서드를 구현
public class zSecurityConfig extends WebSecurityConfigurerAdapter{

    private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserService userService;
	
	@Autowired
	public zSecurityConfig(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authenticationProvider;
    }

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 실제 인증을 진행할 Provider
//      auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
		auth.authenticationProvider(authenticationProvider(userService));
	}

	// Security 설정을 잡아줌
	@Override
	public void configure(WebSecurity web) throws Exception {
		//이미지,자바스크립트,css 디렉토리 보안 설정 
		// static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		http
//			.authorizeRequests()
//				.antMatchers("/user/save").permitAll()
//				.antMatchers("/").hasAnyAuthority("ADMIN","USER")
//				.anyRequest().authenticated()
//			.and()
//				.csrf().ignoringAntMatchers("/user/save")
//			.and()
//				.formLogin()
//				.defaultSuccessUrl("/")
//			.and()
//				.logout()
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//				.deleteCookies("JSESSIONID")
//			.and()
//				.exceptionHandling()
//				.accessDeniedPage("/access-denied");
//	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//HTTP 관련 보안 설정 **가장 중요
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/user/**", "/program/**").hasRole("ADMIN")
                .antMatchers("/user/list").hasRole("USER")
                .antMatchers("/**").permitAll()
            .and() // 로그인 설정
                .formLogin()
                .loginPage("/main/login")
                .defaultSuccessUrl("/main/branch")
                .usernameParameter("user_idname")
                .permitAll()
            .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) // 로그아웃의 기본 URL(/logout) 이 아닌 다른 URL로 재정의합니다.
                .logoutSuccessUrl("/user/login")
                .invalidateHttpSession(true) // HTTP 세션을 초기화하는 작업입니다.
            .and()
                // 403 예외처리 핸들링
                 .exceptionHandling().accessDeniedPage("/user/denied");
    }

	// Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		// Spring Security에서 제공하는 비밀번호 암호화 객체
//		return new BCryptPasswordEncoder();
//	}
}
