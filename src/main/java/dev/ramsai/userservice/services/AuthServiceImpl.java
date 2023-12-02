package dev.ramsai.userservice.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.ramsai.userservice.dtos.UserDto;
import dev.ramsai.userservice.models.SessionStatus;
import dev.ramsai.userservice.models.User;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public ResponseEntity<UserDto> login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> logout(String token, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto signUp(String email, String password) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		
		return null;
	}

	@Override
	public SessionStatus validate(String token, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}