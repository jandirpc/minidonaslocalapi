package com.donibites.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.donibites.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}