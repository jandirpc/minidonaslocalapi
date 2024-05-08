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
import com.donibites.models.Product;
import com.donibites.repositories.CategoryRepository;
import com.donibites.repositories.ProductRepository;

/**
 * Products transactions controller
 */
@RestController
public class ProductController {
	
	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	/**
	 * Return list of products
	 */
    @GetMapping("/products")
    List<Product> findAll() {
        return productRepository.findAll();
    }
	

    /**
     * Creates a new Product 
     * @RequestBody JSON representation of a Product
     * @return Product
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/products/newProduct")
    Product newProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }
    
    /**
     * Search an Product 
     * @param id id of the product
     * @return Product
     */
    @GetMapping("/products/find/{id}")
    Product findProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    
    /**
     * Update an Product 
     * @param id id of the product
     * @return Product
     */
    @PutMapping("/products/update/{id}")
    Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
    	Category cat = categoryRepository.findById(product.getCategory().getId()).get();
        return productRepository.findById(id)
                .map(x -> {
                	x.setName(product.getName());
                	x.setDescription(product.getDescription());
                	x.setQty(product.getQty());
                	x.setCategory(cat);
                	x.setUnitPrice(product.getUnitPrice());
                	x.setStatus(product.getStatus());
                    return productRepository.save(x);
                })
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    
    /**
     * Deactivate an Product 
     * @param id id of the product
     * @return boolean
     */
    @GetMapping("/products/delete/{id}")
    Boolean deactivateProduct(@PathVariable Long id) {

        Product deleteProduct = productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
        deleteProduct.setStatus("inactivo");
        productRepository.save(deleteProduct);
        return true;

    }

}
