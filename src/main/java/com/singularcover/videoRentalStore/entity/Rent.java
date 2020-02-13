package com.singularcover.videoRentalStore.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RENT")
public class Rent {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_rent_seq")
	private Integer idRent;

	@ManyToOne
	@JoinColumn(name = "idCustomer")
	@NotNull
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "idFilm")
	@NotNull
	private Film film;

	@NotNull
	private Date dateRent;
	private Date dateReturn;
	private Integer points;
	@NotNull
	private Integer days;

	public Rent() {}

	public Rent(Integer idRent, Customer customer, Film film, Date dateRent, Date dateReturn, Integer points,
			Integer days) {
		this.idRent = idRent;
		this.customer = customer;
		this.film = film;
		this.dateRent = dateRent;
		this.points = points;
		this.setDays(days);
	}

	public Rent(Customer customer, Film film, Date dateRent, Integer points, Integer days) {
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

	public static RentBuilder builder() {
		return new RentBuilder();
	}

	public static class RentBuilder {
		private Integer idRent;
		private Customer customer;
		private Film film;
		private Date dateRent;
		private Date dateReturn;
		private Integer points;
		private Integer days;

		public Rent build() {
			return new Rent(idRent, customer, film, dateRent, dateReturn, points, days);
		}

		public RentBuilder setIdRent(Integer idRent) {
			this.idRent = idRent;
			return this;
		}

		public RentBuilder setCustomer(Customer customer) {
			this.customer = customer;
			return this;
		}

		public RentBuilder setFilm(Film film) {
			this.film = film;
			return this;
		}

		public RentBuilder setDateRent(Date dateRent) {
			this.dateRent = dateRent;
			return this;
		}

		public RentBuilder setDateReturn(Date dateReturn) {
			this.dateReturn = dateReturn;
			return this;
		}

		public RentBuilder setPoints(Integer points) {
			this.points = points;
			return this;
		}

		public RentBuilder setDays(Integer days) {
			this.days = days;
			return this;
		}
	}
}