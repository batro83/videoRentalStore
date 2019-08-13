package com.singularcover.videoRentalStore.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RENT")
public class Rent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idRent;

	@ManyToOne
	@JoinColumn(name = "idCustomer")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "idFilm")
	private Film film;

	private Date dateRent;
	private Date dateReturn;
	private Integer points;
	private Integer days;
	
	public Rent() {	}

	public Rent(Customer customer, Film film, Date dateRent, Integer points, Integer days) {
		super();
		this.customer = customer;
		this.film = film;
		this.dateRent = dateRent;
		this.points = points;
		this.setDays(days);
	}

	public Integer getIdRent() {
		return idRent;
	}

	public void setIdRent(Integer idRent) {
		this.idRent = idRent;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
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

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

}
