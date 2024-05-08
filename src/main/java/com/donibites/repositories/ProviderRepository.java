package com.donibites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donibites.models.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
	
}