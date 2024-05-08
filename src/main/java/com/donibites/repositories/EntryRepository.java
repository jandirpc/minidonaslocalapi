package com.donibites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donibites.models.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {
	
}