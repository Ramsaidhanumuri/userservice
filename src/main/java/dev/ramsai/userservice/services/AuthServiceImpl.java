package dev.ramsai.userservice.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.ramsai.userservice.dtos.UserDto;
import dev.ramsai.userservice.models.SessionStatus;
import dev.ramsai.userservice.models.User;
import dev.ramsai.userservice.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRepository userRepository;

	@Override
	public ResponseEntity<UserDto> login(String email, String password) {
		
		Optional<User> userOptional = userRepository.findByEmail(email);
		
		if(userOptional.isEmpty()) {
			return null;
		}
		
		User user = userOptional.get();
		
		if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("wrong password!!!");
		}
		
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

		User savedUser = userRepository.save(user);
		
		return UserDto.from(savedUser);
	}

	@Override
	public SessionStatus validate(String token, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
