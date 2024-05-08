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
import com.donibites.models.Order;
import com.donibites.models.Invoice;
import com.donibites.repositories.OrderRepository;
import com.donibites.repositories.InvoiceRepository;

/**
 * Invoices transactions controller
 */
@RestController
public class InvoiceController {
	
	@Autowired
    private InvoiceRepository invoiceRepository;
	
	@Autowired
    private OrderRepository orderRepository;
	
	/**
	 * Return list of invoices
	 */
    @GetMapping("/invoices")
    List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }
	

    /**
     * Creates a new Invoice 
     * @RequestBody JSON representation of a Invoice
     * @return Invoice
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/invoices/newInvoice")
    Invoice newInvoice(@RequestBody Invoice newInvoice) {
        return invoiceRepository.save(newInvoice);
    }
    
    /**
     * Search an Invoice 
     * @param id id of the invoice
     * @return Invoice
     */
    @GetMapping("/invoices/find/{id}")
    Invoice findInvoice(@PathVariable Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    
    /**
     * Update an Invoice 
     * @param id id of the invoice
     * @return Invoice
     */
    @PutMapping("/invoices/update/{id}")
    Invoice updateInvoice(@RequestBody Invoice invoice, @PathVariable Long id) {
    	Order order = orderRepository.findById(invoice.getOrder().getId()).get();
        return invoiceRepository.findById(id)
                .map(x -> {
                	x.setInvoiceDate(invoice.getInvoiceDate());
                	x.setDescription(invoice.getDescription());
                	x.setRuc(invoice.getRuc());
                	x.setOrder(order);
                	x.setPaidAmount(invoice.getPaidAmount());
                	x.setStatus(invoice.getStatus());
                    return invoiceRepository.save(x);
                })
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    
    /**
     * Deactivate an Invoice 
     * @param id id of the invoice
     * @return boolean
     */
    @GetMapping("/invoices/delete/{id}")
    Boolean deactivateInvoice(@PathVariable Long id) {

        Invoice deleteInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
        deleteInvoice.setStatus("eliminada");
        invoiceRepository.save(deleteInvoice);
        return true;

    }

}
