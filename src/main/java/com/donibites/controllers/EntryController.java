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
import com.donibites.models.OrderDetail;
import com.donibites.models.Product;
import com.donibites.repositories.OrderDetailRepository;
import com.donibites.repositories.OrderRepository;
import com.donibites.repositories.ProductRepository;

/**
 * Orders transactions controller
 */
@RestController
public class EntryController {
	
	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
    private OrderDetailRepository orderDetailRepository;
	
	@Autowired
    private ProductRepository productRepository;
	
	/**
	 * Return list of orders
	 */
    @GetMapping("/orders")
    List<Order> findAll() {
        return orderRepository.findAll();
    }
	

    /**
     * Creates a new Order 
     * @RequestBody JSON representation of a Order
     * @return Order
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/orders/newOrder")
    Order newOrder(@RequestBody Order newOrder) {
    	orderRepository.save(newOrder);
    	for(OrderDetail detail:newOrder.getDetails()) {
    		detail.setOrder(newOrder);
    		orderDetailRepository.save(detail);
    		Product producto = productRepository.findById(detail.getProduct().getId())
    				.orElseThrow(() -> new ObjectNotFoundException(detail.getProduct().getId()));
    		producto.setQty(producto.getQty()-detail.getQty());
    		productRepository.save(producto);
    	}
        return newOrder;
    }
    
    /**
     * Search an Order 
     * @param id id of the order
     * @return Order
     */
    @GetMapping("/orders/find/{id}")
    Order findOrder(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
    
    /**
     * Update an Order and the details
     * @param id id of the order
     * @return Order
     */
    @PutMapping("/orders/update/{id}")
    Order updateOrder(@RequestBody Order order, @PathVariable Long id) {
    	
    	Order orderToUpdate = orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
    	
    	orderToUpdate.setUpdateUser(order.getUpdateUser());
    	orderToUpdate.setOrderDate(order.getOrderDate());
    	orderToUpdate.setUpdateDate(order.getUpdateDate());
    	orderToUpdate.setExpectedDeliverDate(order.getExpectedDeliverDate());
    	orderToUpdate.setDeliverDate(order.getDeliverDate());
    	orderToUpdate.setDescription(order.getDescription());
    	orderToUpdate.setStatus(order.getStatus());
        orderRepository.save(order);
        for(OrderDetail detail:order.getDetails()) {
        	OrderDetail detalleAnterior = orderDetailRepository.findById(detail.getId())
                    .orElseThrow(() -> new ObjectNotFoundException(detail.getId()));
        	Integer cantidadAnterior = detalleAnterior.getQty();
        	detail.setOrder(orderToUpdate);
    		orderDetailRepository.save(detail);
    		//Actualizar producto
    		Product producto = productRepository.findById(detail.getProduct().getId())
    				.orElseThrow(() -> new ObjectNotFoundException(detail.getProduct().getId()));
    		producto.setQty(producto.getQty()+cantidadAnterior-detail.getQty());
    		productRepository.save(producto);
    	}
        return orderToUpdate;
    }
    
    /**
     * Deactivate an Order 
     * @param id id of the order
     * @return boolean
     */
    @GetMapping("/orders/delete/{id}")
    Boolean deactivateOrder(@PathVariable Long id) {

        Order deleteOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
        deleteOrder.setStatus("cancelado");
        orderRepository.save(deleteOrder);
        for(OrderDetail detail:deleteOrder.getDetails()) {
        	detail.setOrder(deleteOrder);
    		Product producto = productRepository.findById(detail.getProduct().getId())
    				.orElseThrow(() -> new ObjectNotFoundException(detail.getProduct().getId()));
    		producto.setQty(producto.getQty()+detail.getQty());
    		productRepository.save(producto);
    	}
        return true;

    }
    
    /**
     * Process an Order 
     * @param id id of the order
     * @return boolean
     */
    @GetMapping("/orders/process/{id}")
    Boolean processOrder(@PathVariable Long id) {

        Order processOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
        processOrder.setStatus("en_proceso");
        orderRepository.save(processOrder);
        return true;

    }
    
    /**
     * Complete an Order 
     * @param id id of the order
     * @return boolean
     */
    @GetMapping("/orders/complete/{id}")
    Boolean completeOrder(@PathVariable Long id) {

        Order completeOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
        completeOrder.setStatus("completado");
        orderRepository.save(completeOrder);
        return true;

    }    

}
