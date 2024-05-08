package com.donibites.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.donibites.exceptions.ObjectNotFoundException;
import com.donibites.models.Category;
import com.donibites.repositories.CategoryRepository;

/**
 * Category transactions controller
 */
@RestController
public class CategoryController {
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	/**
	 * Return list of Categories
	 */
    @GetMapping("/categories")
    List<Category> findAll() {
        return categoryRepository.findAll();
    }
	

    /**
     * Creates a new Category
     * @RequestBody JSON representation of a Category
     * @return Category
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/categories/newCategory")
    Category newCategory(@RequestBody Category newCategory) {
        return categoryRepository.save(newCategory);
    }
    
    /**
     * Search an Category 
     * @param id id of the Category
     * @return Category
     */
    @GetMapping("/categories/find/{id}")
    Category findCategory(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    
    /**
     * Update an Category 
     * @param id id of the Category
     * @return Category
     */
    @PutMapping("/categories/update/{id}")
    Category updateCategory(@RequestBody Category category, @PathVariable Long id) {

        return categoryRepository.findById(id)
                .map(x -> {
                	x.setName(category.getName());
                	return categoryRepository.save(x);
                })
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    

}
