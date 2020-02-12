package com.singularcover.videoRentalStore.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id
	private Long idCustomer;
	private String name;
	private Integer points;

	public Customer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Customer() {
	}

	public Customer(Long idCustomer, String name, Integer points) {
		super();
		this.idCustomer = idCustomer;
		this.name = name;
		this.points = points;
	}

	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public static CustomerBuilder builder() {
		return new CustomerBuilder();
	}

	public static class CustomerBuilder {
		private Long idCustomer;
		private String name;
		private Integer points;

		public Customer build() {
			return new Customer(idCustomer, name, points);
		}

		public CustomerBuilder setIdCustomer(Long idCustomer) {
			this.idCustomer = idCustomer;
			return this;
		}

		public CustomerBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public CustomerBuilder setPoints(Integer points) {
			this.points = points;
			return this;
		}
	}
}