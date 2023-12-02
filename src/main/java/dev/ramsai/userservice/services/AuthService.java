package dev.ramsai.userservice.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.support.SessionStatus;

public interface AuthService {

	ResponseEntity<UserDto> login(String email, String password);
	
	ResponseEntity<Void> logout(String token, Long userId);
	
	UserDto signUp(String email, String password);
	
	SessionStatus validate(String token, Long userId);
}
