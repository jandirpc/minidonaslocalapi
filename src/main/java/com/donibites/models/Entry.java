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

@Entity(name = "tb_ingreso_producto")
public class Entry {
	@Id
    @Column(name = "id_ingreso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_usuario")
	private Integer recordUser;
	@Column(name = "fecha_ingreso")
	private Date recordDate;
	@Column(name = "descripcion")
	private String description;

	@OneToMany(mappedBy = "entry")
	private List<EntryDetail> details;

	public Entry() {
		super();
		// TODO Auto-generated constructor stub
	}


	

	


	public Entry(Long id, Integer recordUser, Date recordDate, String description, List<EntryDetail> details) {
		super();
		this.id = id;
		this.recordUser = recordUser;
		this.recordDate = recordDate;
		this.description = description;
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







	public Date getRecordDate() {
		return recordDate;
	}







	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}







	public String getDescription() {
		return description;
	}







	public void setDescription(String description) {
		this.description = description;
	}







	public List<EntryDetail> getDetails() {
		return details;
	}







	public void setDetails(List<EntryDetail> details) {
		this.details = details;
	}







	@Override
	public String toString() {
		return description;
	}

}
