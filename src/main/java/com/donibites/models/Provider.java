package com.donibites.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 *  
 *  
 */
@Entity(name = "tb_proveedor")
public class Provider {
	@Id
    @Column(name = "id_proveedor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "proveedor")
	private String name;
	@Column(name = "direccion")
	private String address;
	@Column(name = "telefono")
	private String phone;
	@Column(name = "correo_electronico")
	private String email;
	@Column(name = "estado_proveedor")
	private String status="activo";

	

	public Provider() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Provider(Long id, String name, String address, String phone, String email, String status) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
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



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
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
