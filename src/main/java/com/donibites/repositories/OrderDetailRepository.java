package com.donibites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donibites.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	
}