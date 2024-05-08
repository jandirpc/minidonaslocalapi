package com.donibites.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


/**
 *  
 *  
 */

@Entity(name = "tb_pedido")
public class Order {
	@Id
    @Column(name = "id_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_usuario_creacion")
	private Integer recordUser;
	@Column(name = "id_usuario_actualizacion")
	private Integer updateUser;
	@Column(name = "fecha_pedido")
	private Date orderDate;
	@Column(name = "fecha_actualizacion")
	private Date updateDate;
	@Column(name = "fecha_entrega_esperada")
	private Date expectedDeliverDate;
	@Column(name = "fecha_hora_entrega")
	private Date deliverDate;
	@Column(name = "descripcion")
	private String description;
	@Column(name = "estado_pedido")
	private String status="activo";

	@OneToMany(mappedBy = "order")
	private List<OrderDetail> details;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}


	

	public Order(Long id, Integer recordUser, Integer updateUser, Date orderDate, Date updateDate,
			Date expectedDeliverDate, Date deliverDate, String description, String status, List<OrderDetail> details) {
		super();
		this.id = id;
		this.recordUser = recordUser;
		this.updateUser = updateUser;
		this.orderDate = orderDate;
		this.updateDate = updateDate;
		this.expectedDeliverDate = expectedDeliverDate;
		this.deliverDate = deliverDate;
		this.description = description;
		this.status = status;
		this.details = details;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Integer getRecordUser() {
		return recordUser;
	}




	public void setRecordUser(Integer recordUser) {
		this.recordUser = recordUser;
	}




	public Integer getUpdateUser() {
		return updateUser;
	}




	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}




	public Date getOrderDate() {
		return orderDate;
	}




	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}




	public Date getUpdateDate() {
		return updateDate;
	}




	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}




	public Date getExpectedDeliverDate() {
		return expectedDeliverDate;
	}




	public void setExpectedDeliverDate(Date expectedDeliverDate) {
		this.expectedDeliverDate = expectedDeliverDate;
	}




	public Date getDeliverDate() {
		return deliverDate;
	}




	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public List<OrderDetail> getDetails() {
		return details;
	}




	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}




	@Override
	public String toString() {
		return description;
	}

}
