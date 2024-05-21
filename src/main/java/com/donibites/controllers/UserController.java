package com.donibites.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.donibites.exceptions.ObjectNotFoundException;
import com.donibites.models.Role;
import com.donibites.models.User;
import com.donibites.repositories.RoleRepository;
import com.donibites.repositories.UserRepository;

/**
 * Users transactions controller
 */
@RestController
public class UserController {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository;
	
	/**
	 * Return list of users
	 */
    @GetMapping("/users")
    List<User> findAll() {
        return userRepository.findAll();
    }
	

    /**
     * Creates a new User 
     * @RequestBody JSON representation of a User
     * @return User
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/newUser")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }
    
    /**
     * Search an User 
     * @param id id of the user
     * @return User
     */
    @GetMapping("/users/find/{id}")
    User findUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    
    /**
     * Update an User 
     * @param id id of the user
     * @return User
     */
    @PutMapping("/users/update/{id}")
    User updateUser(@RequestBody User user, @PathVariable Long id) {
    	Role role = roleRepository.findById(user.getRol().getId()).get();
        return userRepository.findById(id)
                .map(x -> {
                	x.setUsername(user.getUsername());
                	x.setPassword(user.getPassword());
                	x.setEmail(user.getEmail());
                	x.setRol(role);
                	x.setCompleteName(user.getCompleteName());
                	x.setAddress(user.getAddress());
                	x.setPhone(user.getPhone());
                	x.setStatus(user.getStatus());
                    return userRepository.save(x);
                })
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    
    /**
     * Deactivate an User 
     * @param id id of the user
     * @return boolean
     */
    @GetMapping("/users/delete/{id}")
    Boolean deactivateUser(@PathVariable Long id) {

        User deleteUser = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
        deleteUser.setStatus("inactivo");
        userRepository.save(deleteUser);
        return true;

    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(foundUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    
}
