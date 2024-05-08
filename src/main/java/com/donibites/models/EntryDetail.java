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
@Entity(name = "tb_ingreso_producto_detalle")
@JsonIgnoreProperties({"order"})
public class EntryDetail {
	@Id
    @Column(name = "id_ingreso_detalle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "id_ingreso", referencedColumnName = "id_ingreso")
	private Entry entry;
	
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Product product;
	
	@Column(name = "cantidad")
	private Integer qty;
	


	public EntryDetail() {
		super();
		// TODO Auto-generated constructor stub
	}



	public EntryDetail(Long id, Entry entry, Product product, Integer qty) {
		super();
		this.id = id;
		this.entry = entry;
		this.product = product;
		this.qty = qty;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Entry getEntry() {
		return entry;
	}



	public void setEntry(Entry entry) {
		this.entry = entry;
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


	
	


}
