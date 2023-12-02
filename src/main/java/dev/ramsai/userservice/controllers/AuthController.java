package dev.ramsai.userservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ramsai.userservice.dtos.LoginRequestDto;
import dev.ramsai.userservice.dtos.LogoutRequestDto;
import dev.ramsai.userservice.dtos.SignUpRequestDto;
import dev.ramsai.userservice.dtos.UserDto;
import dev.ramsai.userservice.dtos.ValidateTokenRequestDto;
import dev.ramsai.userservice.models.SessionStatus;
import dev.ramsai.userservice.services.AuthService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto request){
		return authService.login(request.getEmail(), request.getPassword());
	}
	
	@PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto request) {
        return authService.logout(request.getToken(), request.getUserId());
    }
	
	@PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto request) {
        UserDto userDto = authService.signUp(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
	
	@PostMapping("/validate")
    public ResponseEntity<SessionStatus> validateToken(ValidateTokenRequestDto request) {
        SessionStatus sessionStatus = authService.validate(request.getToken(), request.getUserId());

        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
}
