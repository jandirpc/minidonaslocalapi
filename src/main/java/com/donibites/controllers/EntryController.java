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
import com.donibites.models.Entry;
import com.donibites.models.EntryDetail;
import com.donibites.models.Product;
import com.donibites.repositories.EntryDetailRepository;
import com.donibites.repositories.EntryRepository;
import com.donibites.repositories.ProductRepository;

/**
 * Entries transactions controller
 */
@RestController
public class EntryController {
	
	@Autowired
    private EntryRepository entryRepository;
	
	@Autowired
    private EntryDetailRepository entryDetailRepository;
	
	@Autowired
    private ProductRepository productRepository;
	
	/**
	 * Return list of entries
	 */
    @GetMapping("/entries")
    List<Entry> findAll() {
        return entryRepository.findAll();
    }
	

    /**
     * Creates a new Entry 
     * @RequestBody JSON representation of a Entry
     * @return Entry
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/entries/newEntry")
    Entry newEntry(@RequestBody Entry newEntry) {
    	entryRepository.save(newEntry);
    	for(EntryDetail detail:newEntry.getDetails()) {
    		detail.setEntry(newEntry);
    		entryDetailRepository.save(detail);
    		Product producto = productRepository.findById(detail.getProduct().getId())
    				.orElseThrow(() -> new ObjectNotFoundException(detail.getProduct().getId()));
    		producto.setQty(producto.getQty()-detail.getQty());
    		productRepository.save(producto);
    	}
        return newEntry;
    }
    
    /**
     * Search an Entry 
     * @param id id of the entry
     * @return Entry
     */
    @GetMapping("/entries/find/{id}")
    Entry findEntry(@PathVariable Long id) {
        return entryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    
    /**
     * Update an Entry and the details
     * @param id id of the entry
     * @return Entry
     */
    @PutMapping("/entries/update/{id}")
    Entry updateEntry(@RequestBody Entry entry, @PathVariable Long id) {
    	
    	Entry entryToUpdate = entryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
    	
    	entryToUpdate.setRecordUser(entry.getRecordUser());
    	entryToUpdate.setRecordDate(entry.getRecordDate());
    	entryToUpdate.setDescription(entry.getDescription());
        entryRepository.save(entry);
        for(EntryDetail detail:entry.getDetails()) {
        	EntryDetail detalleAnterior = entryDetailRepository.findById(detail.getId())
                    .orElseThrow(() -> new ObjectNotFoundException(detail.getId()));
        	Integer cantidadAnterior = detalleAnterior.getQty();
        	detail.setEntry(entryToUpdate);
    		entryDetailRepository.save(detail);
    		//Actualizar producto
    		Product producto = productRepository.findById(detail.getProduct().getId())
    				.orElseThrow(() -> new ObjectNotFoundException(detail.getProduct().getId()));
    		producto.setQty(producto.getQty()+cantidadAnterior-detail.getQty());
    		productRepository.save(producto);
    	}
        return entryToUpdate;
    }
    

}
