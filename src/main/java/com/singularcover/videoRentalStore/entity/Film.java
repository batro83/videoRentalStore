package com.singularcover.videoRentalStore.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FILM")
public class Film {
	
	@Id
	@GeneratedValue
	private Long idFilm;
	
	private String name;
	
	private String category;
	
	private Date dateCreated;
	
	@ManyToOne
    @JoinColumn(name = "idTypeFilm")
	private TypeFilm type;
	
	
	
	
	public TypeFilm getType() {
		return type;
	}
	public void setType(TypeFilm type) {
		this.type = type;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Long getIdFilm() {
		return idFilm;
	}
	public void setIdFilm(Long idFilm) {
		this.idFilm = idFilm;
	}
	
	
	

}
