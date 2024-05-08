package com.donibites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donibites.models.EntryDetail;

public interface EntryDetailRepository extends JpaRepository<EntryDetail, Long> {
	
}