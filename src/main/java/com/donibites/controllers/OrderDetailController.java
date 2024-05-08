package com.donibites.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.donibites.exceptions.ObjectNotFoundException;
import com.donibites.models.OrderDetail;
import com.donibites.models.Product;
import com.donibites.repositories.OrderDetailRepository;
import com.donibites.repositories.ProductRepository;

/**
 * OrderDetails transactions controller
 */
@RestController
public class OrderDetailController {
	
	@Autowired
    private OrderDetailRepository orderDetailRepository;
	
	@Autowired
    private ProductRepository productRepository;
	
	/**
	 * Return list of orderDetails
	 */
    @GetMapping("/orderDetails")
    List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }
	

    /**
     * Creates a new OrderDetail 
     * @RequestBody JSON representation of a OrderDetail
     * @return OrderDetail
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/orderDetails/newOrderDetail")
    OrderDetail newOrderDetail(@RequestBody OrderDetail newOrderDetail) {
        return orderDetailRepository.save(newOrderDetail);
    }
    
    /**
     * Search an OrderDetail 
     * @param id id of the orderDetail
     * @return OrderDetail
     */
    @GetMapping("/orderDetails/find/{id}")
    OrderDetail findOrderDetail(@PathVariable Long id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    
    @DeleteMapping("/orderDetails/delete/{id}")
    Boolean deleteOrderDetail(@PathVariable Long id) {
    	OrderDetail aBorrar = orderDetailRepository.findById(id)
        	.orElseThrow(() -> new ObjectNotFoundException(id));
    	Product producto = productRepository.findById(aBorrar.getProduct().getId())
				.orElseThrow(() -> new ObjectNotFoundException(aBorrar.getProduct().getId()));
		producto.setQty(producto.getQty()+aBorrar.getQty());
		orderDetailRepository.deleteById(id);
    	return true;
    }
   
}
