package com.donibites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donibites.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
}