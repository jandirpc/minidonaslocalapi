package com.donibites.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


/**
 *  
 *  
 */
@Entity(name = "tb_pedido_detalle")
@JsonIgnoreProperties({"order"})
public class OrderDetail {
	@Id
    @Column(name = "id_pedido_detalle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Product product;
	
	@Column(name = "cantidad")
	private Integer qty;
	
	
	@Column(name = "precio_venta")
	private Double price;


	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OrderDetail(Long id, Order order, Product product, Integer qty, Double price) {
		super();
		this.id = id;
		this.order = order;
		this.product = product;
		this.qty = qty;
		this.price = price;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Integer getQty() {
		return qty;
	}


	public void setQty(Integer qty) {
		this.qty = qty;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	


}
