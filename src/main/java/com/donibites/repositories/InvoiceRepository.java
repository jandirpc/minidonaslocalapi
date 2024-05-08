package com.donibites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donibites.models.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	
}