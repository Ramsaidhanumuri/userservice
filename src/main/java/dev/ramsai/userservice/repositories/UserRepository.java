package dev.ramsai.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ramsai.userservice.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
