package com.singularcover.videoRentalStore.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Rent {

	@Id
	@GeneratedValue
	private Integer idRent;

	@ManyToOne
	@JoinColumn(name = "idCustomer")
	private Customer idCustomer;

	@ManyToOne
	@JoinColumn(name = "idFilm")
	private Film idFilm;

	private Date dateRent;
	private Date dateReturn;
	private Integer points;

	public Rent(Customer idCustomer, Film idFilm, Date dateRent, Integer points) {
		super();
		this.idCustomer = idCustomer;
		this.idFilm = idFilm;
		this.dateRent = dateRent;
		this.points = points;
	}

	public Integer getIdRent() {
		return idRent;
	}

	public void setIdRent(Integer idRent) {
		this.idRent = idRent;
	}

	public Customer getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Customer idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Film getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(Film idFilm) {
		this.idFilm = idFilm;
	}

	public Date getDateRent() {
		return dateRent;
	}

	public void setDateRent(Date dateRent) {
		this.dateRent = dateRent;
	}

	public Date getDateReturn() {
		return dateReturn;
	}

	public void setDateReturn(Date dateReturn) {
		this.dateReturn = dateReturn;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

}
