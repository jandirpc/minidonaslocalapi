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
import com.donibites.models.User;
import com.donibites.models.Entry;
import com.donibites.repositories.UserRepository;
import com.donibites.repositories.EntryRepository;

/**
 * Entries transactions controller
 */
@RestController
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Return list of products
     */
    @GetMapping("/entries")
    List<Entry> findAll() {
        return entryRepository.findAll();
    }


    /**
     * Creates a new Entries
     *
     * @return Entry
     * @RequestBody JSON representation of a Entry
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/entries/newEntry")
    Entry newEntry(@RequestBody Entry newEntry) {
        return entryRepository.save(newEntry);
    }

    /**
     * Search an Entry
     *
     * @param id id of the entry
     * @return Entry
     */
    @GetMapping("/entries/find/{id}")
    Entry findEntry(@PathVariable Long id) {
        return entryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }

    /**
     * Update an Entry
     *
     * @param id id of the entry
     * @return Entry
     */
    @PutMapping("/entries/update/{id}")
    Entry updateEntry(@RequestBody Entry entry, @PathVariable Long id) {
        // Buscar el usuario por su ID
        User user = userRepository.findById(entry.getRecordUser()).orElseThrow(() -> new ObjectNotFoundException(entry.getRecordUser()));

        return entryRepository.findById(id)
                .map(x -> {
                    x.setRecordUser(user.getId()); // Establecer el ID del usuario en la entrada
                    x.setRecordDate(entry.getRecordDate());
                    x.setDescription(entry.getDescription());
                    return entryRepository.save(x);
                })
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }


    /**
     * Deactivate an Entry
     *
     * @param id id of the entry
     * @return boolean
     */
    @GetMapping("/entries/delete/{id}")
    Boolean deactivateEntry(@PathVariable Long id) {

        Entry deleteEntry = entryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
        entryRepository.save(deleteEntry);
        return true;

    }
}