package com.eltov.air.module.inside.user.session;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.eltov.air.module.inside.user.DTO.UserDTO;

public class UserSession extends User  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UserDTO user;
	
	public UserSession(UserDTO user) {
		super(user.getUser_idname(), user.getUser_passwd(), authorities(user.getUser_auth()));
		this.user = user;
	}
	
	private static Collection<? extends GrantedAuthority> authorities(String...roles) {
        return Arrays.asList(roles).stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toSet());
    }
	
	public UserDTO getUser() {
		//if(user == null)  throw new ExceptionWrapper(ExceptionCode.E999_USER_SESSION_EMPTY, new Exception("로그인하지 않은 유저"));
		
		return user;
	}
}
