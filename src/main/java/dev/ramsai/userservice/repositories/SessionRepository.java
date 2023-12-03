package dev.ramsai.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ramsai.userservice.models.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {

	Optional<Session> findByTokenAndUser_Id(String token, Long userId);
	
}
