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
import com.donibites.models.Provider;
import com.donibites.repositories.ProviderRepository;

/**
 * Providers transactions controller
 */
@RestController
public class ProviderController {
	
	@Autowired
    private ProviderRepository providerRepository;
	
	/**
	 * Return list of providers
	 */
    @GetMapping("/providers")
    List<Provider> findAll() {
        return providerRepository.findAll();
    }
	

    /**
     * Creates a new Provider 
     * @RequestBody JSON representation of a Provider
     * @return Provider
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/providers/newProvider")
    Provider newProvider(@RequestBody Provider newProvider) {
        return providerRepository.save(newProvider);
    }
    
    /**
     * Search an Provider 
     * @param id id of the provider
     * @return Provider
     */
    @GetMapping("/providers/find/{id}")
    Provider findProvider(@PathVariable Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    
    /**
     * Update an Provider 
     * @param id id of the provider
     * @return Provider
     */
    @PutMapping("/providers/update/{id}")
    Provider updateProvider(@RequestBody Provider provider, @PathVariable Long id) {

        return providerRepository.findById(id)
                .map(x -> {
                	x.setName(provider.getName());
                	x.setEmail(provider.getEmail());
                	x.setAddress(provider.getAddress());
                	x.setPhone(provider.getPhone());
                	x.setStatus(provider.getStatus());
                    return providerRepository.save(x);
                })
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    
    /**
     * Deactivate an Provider 
     * @param id id of the provider
     * @return boolean
     */
    @GetMapping("/providers/delete/{id}")
    Boolean deactivateProvider(@PathVariable Long id) {

        Provider deleteProvider = providerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
        deleteProvider.setStatus("inactivo");
        providerRepository.save(deleteProvider);
        return true;

    }

}
