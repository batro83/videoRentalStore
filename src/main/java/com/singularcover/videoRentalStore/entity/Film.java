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
@Table(name = "FILM")
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_film_seq")
	private Long idFilm;

	private String name;

	private String category;

	private Date dateCreated;

	@ManyToOne
	@JoinColumn(name = "idTypeFilm")
	private TypeFilm type;

	public Film() {}

	private Film(Long idFilm, String name, String category, Date dateCreated, TypeFilm type) {
		this.idFilm = idFilm;
		this.name = name;
		this.category = category;
		this.dateCreated = dateCreated;
		this.type = type;
	}

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

	public static FilmBuilder builder() {
		return new FilmBuilder();
	}

	public static class FilmBuilder {

		private Long idFilm;
		private String name;
		private String category;
		private Date dateCreated;
		private TypeFilm type;

		public Film build() {
			return new Film(idFilm, name, category, dateCreated, type);
		}

		public FilmBuilder setIdFilm(Long idFilm) {
			this.idFilm = idFilm;
			return this;
		}

		public FilmBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public FilmBuilder setCategory(String category) {
			this.category = category;
			return this;
		}

		public FilmBuilder setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
			return this;
		}

		public FilmBuilder setType(TypeFilm type) {
			this.type = type;
			return this;
		}
	}
}