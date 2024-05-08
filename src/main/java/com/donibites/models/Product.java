package com.donibites.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


/**
 *  
 *  
 */
@Entity(name = "tb_producto")
public class Product {
	@Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "producto")
	private String name;
	@Column(name = "descripcion")
	private String description;
	@Column(name = "existencia")
	private Integer qty;
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Category category;
	@Column(name = "precio")
	private Double unitPrice;
	@Column(name = "estado_producto")
	private String status="activo";

	

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Product(Long id, String name, String description, Integer qty, Category category, Double unitPrice,
			String status) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.qty = qty;
		this.category = category;
		this.unitPrice = unitPrice;
		this.status = status;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Integer getQty() {
		return qty;
	}



	public void setQty(Integer qty) {
		this.qty = qty;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Double getUnitPrice() {
		return unitPrice;
	}



	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return name;
	}
	


}
