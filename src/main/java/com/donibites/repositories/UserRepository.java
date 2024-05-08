package com.donibites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donibites.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}