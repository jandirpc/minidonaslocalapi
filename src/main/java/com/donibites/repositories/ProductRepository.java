package com.donibites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donibites.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}