package com.singularcover.videoRentalStore.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TYPE_FILM")
public class TypeFilm {
			
	@Id
	private Long idTypeFilm;	
	private String description;
	private Integer points;
	private Integer price;
		
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getIdTypeFilm() {
		return idTypeFilm;
	}
	public void setIdTypeFilm(Long idTypeFilm) {
		this.idTypeFilm = idTypeFilm;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

}
