package dev.ramsai.userservice.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.ramsai.userservice.dtos.UserDto;
import dev.ramsai.userservice.models.SessionStatus;

@Service
public class AuthServiceImpl implements AuthService {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionStatus validate(String token, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
