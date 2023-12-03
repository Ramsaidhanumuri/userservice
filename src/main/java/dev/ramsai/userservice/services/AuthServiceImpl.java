package dev.ramsai.userservice.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import dev.ramsai.userservice.dtos.UserDto;
import dev.ramsai.userservice.models.Session;
import dev.ramsai.userservice.models.SessionStatus;
import dev.ramsai.userservice.models.User;
import dev.ramsai.userservice.repositories.SessionRepository;
import dev.ramsai.userservice.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRepository userRepository;
	private SessionRepository sessionRepository;

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
		
		MacAlgorithm alg = Jwts.SIG.HS256;
		SecretKey key = alg.key().build();
		
		Map<String, Object> jsonForJwt = new HashMap<String, Object>();
		jsonForJwt.put("email", user.getEmail());
		jsonForJwt.put("roles", user.getRoles());
		jsonForJwt.put("createdAt", new Date());
        jsonForJwt.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));
        
        String token = Jwts.builder()
        		.claims(jsonForJwt)
        		.signWith(key)
        		.compact();
        
        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        sessionRepository.save(session);
        
        UserDto userDto = UserDto.from(user);
        
        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<String, String>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:"+token);
        
        ResponseEntity<UserDto> response = new ResponseEntity<>(userDto, headers, HttpStatus.OK);
		
		return response;
	}

	@Override
	public ResponseEntity<Void> logout(String token, Long userId) {
		
		Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);
		
		if(sessionOptional.isEmpty()) {
			return null;
		}
		
		Session session = sessionOptional.get();
		session.setSessionStatus(SessionStatus.ENDED);
		sessionRepository.save(session);
		
		return ResponseEntity.ok().build();
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
