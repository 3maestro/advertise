package com.eltov.air.core.config;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.eltov.air.core.util.CommUtil;
import com.eltov.air.module.inside.user.DTO.UserDTO;
import com.eltov.air.module.inside.user.DTO.UserLogDTO;
import com.eltov.air.module.inside.user.service.UserLogService;
import com.eltov.air.module.inside.user.service.UserService;
import com.eltov.air.module.inside.user.session.UserSession;

@Configuration
@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity(debug = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final UserService userService;
	private final UserLogService userServiceLog;

	@Autowired
	public SecurityConfig(UserService userService, UserLogService userServiceLog) {
		this.userService = userService;
		this.userServiceLog = userServiceLog;
	}
	
	//BCryptPasswordEncoder??? ????????? ?????? ??? strength??? ????????? ??? ?????????, ?????? round ?????? ???????????? ????????????.
	//BCryptPasswordEncoder ???????????? ?????? ????????? ???????????? encode, matches???, ?????? ?????? ????????? ????????? ???????????? ???????????? ?????? ????????? ????????????.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**", "/include/**", "/user/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//iframe ?????? ??? ??? ??????.
		http.headers().frameOptions().sameOrigin()
			.and()
				.csrf().disable()
				.antMatcher("/**").authorizeRequests()
//					.antMatchers("/main/**").hasAuthority("ADMIN")
//					.antMatchers("/branch/**").hasAnyAuthority("SUPER", "DEV")
					.antMatchers("/branch/**").permitAll()
					.antMatchers("/company/**").permitAll()
//				.antMatchers(
//							 "/eltovdid/contents/restaurant/reservation/booking",
//							 "/eltovdid/contents/course/**",
//							 "/eltovdid/c/cs/**",
//							 "/eltovdid/p/**",
//							 "/eltovdid/",
//							 "/eltovdid/system/test/*",
//							 "/eltovdid/system/user/**",
//							 "/eltovdid/dologin",
//							 "/eltovdid/v1/cms/wall/data",
//							 "/").permitAll()
//				.antMatchers("/eltovdid/main/**").hasAuthority("ADMIN")
//				.antMatchers("/eltovdid/system/**").hasAuthority("ADMIN")
//				.antMatchers("/eltovdid/contents/**").hasAuthority("ADMIN")
//				.antMatchers("/eltovdid/**").authenticated()
			.and()
				.formLogin()
				.loginPage("/")
				.loginProcessingUrl("/dologin")
				.usernameParameter("user_idname")
				.passwordParameter("user_passwd")
				.successHandler(setSuccessHandler())
				.failureHandler(setFailureHandler())
				.permitAll()
			.and()
				.logout()
				.logoutUrl("/dologout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
			.and()
				.exceptionHandling()
				.accessDeniedHandler(setAccessDeniedHandler());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	protected AuthenticationSuccessHandler setSuccessHandler() {
		return new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(
												HttpServletRequest req, HttpServletResponse res,
												Authentication authentication
											    ) throws IOException, ServletException {
				
				UserSession userSession = (UserSession)authentication.getPrincipal();
				UserDTO user = userSession.getUser();
				
				String userAuth = user.getUser_auth();
				
				int userId = user.getUser_id();
				String userIdname = user.getUser_idname();
				System.out.println(userId + " <-userId");
				System.out.println(userIdname + " <-userIdname");
				String idsaveYn = CommUtil.getChkNull(req.getParameter("idsave"));
				
				//1. ?????? ??????
				userService.setRememberMeCookie(res, idsaveYn, userIdname);
				//2. ????????? ?????? ????????????
				userService.setUpdateLoginDate(userId);
				//3. ?????? ??????
//				userServiceLog.setLogInsert(new UserModelLog(user.getBrn_id(), user.getUser_id(), "", "", "USER", user.getUser_name(), 0, "LOGIN", CommUtil.getIp(req)));
				
//				if(StringUtils.equals(user.getUser_auth(), "STORE")) {
//					res.sendRedirect("/eltovdid/contents/restaurant/reservation/view?res_id="+user.getStore_id());
//					return;
//				}
				if(StringUtils.equals(userAuth, "SUPER") || StringUtils.equals(userAuth, "DEV")) {
					res.sendRedirect("/company/list");
					return;
				}else if(StringUtils.equals(userAuth, "ADMIN")){
					res.sendRedirect("/main/dashboard");
					return;
				}
			}
		};
	}
	
	@Bean
	protected AuthenticationFailureHandler setFailureHandler() {
		return new AuthenticationFailureHandler(){
			@Override
			public void onAuthenticationFailure(
												HttpServletRequest req, HttpServletResponse res,
												AuthenticationException exception
												) throws IOException, ServletException {
				
				String errMsg = "???????????? ?????????????????????.";
				
				if(exception instanceof BadCredentialsException) {
					errMsg = "????????? ?????? ??????????????? ???????????? ????????????.";
				}
				
				req.setAttribute("errMsg", errMsg);
				req.getRequestDispatcher("/error").forward(req, res);
			}
			
		};
	}
	
	@Bean
	protected AccessDeniedHandler setAccessDeniedHandler() {
		return new AccessDeniedHandler() {
			
			@Override
			public void handle(HttpServletRequest req, HttpServletResponse res,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				
				res.setStatus(HttpStatus.FORBIDDEN.value());
				
				UserSession userSession = (UserSession)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				Optional<UserSession> maybeUserSession = Optional.ofNullable(userSession); 
				String userAuth = maybeUserSession.map(UserSession::getUser).map(UserDTO::getUser_auth).orElse("ANONYMOUS");
				
				if(StringUtils.equals(userAuth, "DEV") || StringUtils.equals(userAuth, "SUPER")) {
					res.sendRedirect("/branch/list");
					return;
				}else if(StringUtils.equals(userAuth, "ADMIN")) {
					res.sendRedirect("/main/dashboard");
					return;
				}else {
					req.setAttribute("errMsg", "???????????? ???????????????.");
					req.getRequestDispatcher("/error").forward(req, res);
					return;
				}
				
			}
		};
	}
	
	
}
