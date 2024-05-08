package com.donibites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donibites.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}