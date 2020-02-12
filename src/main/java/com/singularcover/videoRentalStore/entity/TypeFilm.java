package com.singularcover.videoRentalStore.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TYPE_FILM")
public class TypeFilm {

	@Id
	private Long idTypeFilm;
	private String description;
	private Integer points;
	private Integer price;

	public TypeFilm() {}

	private TypeFilm(Long idTypeFilm, String description, Integer points, Integer price) {
		this.idTypeFilm = idTypeFilm;
		this.description = description;
		this.points = points;
		this.price = price;
	}

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

	public static TypeFilmBuilder builder() {
		return new TypeFilmBuilder();
	}

	public static class TypeFilmBuilder {

		private Long idTypeFilm;
		private String description;
		private Integer points;
		private Integer price;

		public TypeFilmBuilder setIdTypeFilm(final Long idTypeFilm) {
			this.idTypeFilm = idTypeFilm;
			return this;
		}

		public TypeFilmBuilder setDescription(final String description) {
			this.description = description;
			return this;
		}

		public TypeFilmBuilder setPoints(final Integer points) {
			this.points = points;
			return this;
		}

		public TypeFilmBuilder setPrice(final Integer price) {
			this.price = price;
			return this;
		}

		public TypeFilm build() {
			return new TypeFilm(idTypeFilm, description, points, price);
		}
	}
}