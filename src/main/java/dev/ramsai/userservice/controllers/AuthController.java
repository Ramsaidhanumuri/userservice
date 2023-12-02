package dev.ramsai.userservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ramsai.userservice.dtos.LoginRequestDto;
import dev.ramsai.userservice.dtos.LogoutRequestDto;
import dev.ramsai.userservice.dtos.UserDto;
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
}
