package com.donibites.models;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


/**
 *  
 *  
 */

@Entity(name = "tb_factura")
public class Invoice {
	@Id
    @Column(name = "id_factura")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fecha_emision")
	private Date invoiceDate;
	
	@OneToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
	private Order order;

	@Column(name = "ruc")
	private String ruc;
	@Column(name = "descripcion")
	private String description;
	@Column(name = "pago_total")
	private String paidAmount;
	@Column(name = "estado_factura")
	private String status="activa";


	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Invoice(Long id, Date invoiceDate, Order order, String ruc, String description, String paidAmount,
			String status) {
		super();
		this.id = id;
		this.invoiceDate = invoiceDate;
		this.order = order;
		this.ruc = ruc;
		this.description = description;
		this.paidAmount = paidAmount;
		this.status = status;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Date getInvoiceDate() {
		return invoiceDate;
	}




	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}




	public Order getOrder() {
		return order;
	}




	public void setOrder(Order order) {
		this.order = order;
	}




	public String getRuc() {
		return ruc;
	}




	public void setRuc(String ruc) {
		this.ruc = ruc;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getPaidAmount() {
		return paidAmount;
	}




	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	@Override
	public String toString() {
		return description;
	}

}
