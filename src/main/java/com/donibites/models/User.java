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
@Entity(name = "tb_usuario")
public class User {
	@Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre_usuario")
	private String username;
	@Column(name = "contrasenia")
	private String password;
	@Column(name = "correo_electronico")
	private String email;
	@ManyToOne
	@JoinColumn(name = "id_rol")
	private Role rol;
	@Column(name = "nombre_completo")
	private String completeName;
	@Column(name = "direccion")
	private String address;
	@Column(name = "telefono")
	private String phone;
	@Column(name = "estado_usuario")
	private String status="activo";
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String username, String password, String email, Role rol, String completeName,
			String address, String phone, String status) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.rol = rol;
		this.completeName = completeName;
		this.address = address;
		this.phone = phone;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRol() {
		return rol;
	}

	public void setRol(Role rol) {
		this.rol = rol;
	}

	public String getCompleteName() {
		return completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return username;
	}
	


}
