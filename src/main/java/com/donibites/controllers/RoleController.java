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
import com.donibites.models.Role;
import com.donibites.repositories.RoleRepository;

/**
 * Role transactions controller
 */
@RestController
public class RoleController {
	
	@Autowired
    private RoleRepository roleRepository;
	
	/**
	 * Return list of roles
	 */
    @GetMapping("/roles")
    List<Role> findAll() {
        return roleRepository.findAll();
    }
	

    /**
     * Creates a new Role
     * @RequestBody JSON representation of a Role
     * @return Role
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/roles/newRole")
    Role newRol(@RequestBody Role newRol) {
        return roleRepository.save(newRol);
    }
    
    /**
     * Search an Role 
     * @param id id of the role
     * @return Role
     */
    @GetMapping("/roles/find/{id}")
    Role findRol(@PathVariable Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    
    /**
     * Update an Role 
     * @param id id of the role
     * @return Role
     */
    @PutMapping("/roles/update/{id}")
    Role updateRol(@RequestBody Role role, @PathVariable Long id) {

        return roleRepository.findById(id)
                .map(x -> {
                	x.setName(role.getName());
                	x.setDescription(role.getDescription());
                	return roleRepository.save(x);
                })
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    

}
