package com.singularcover.videoRentalStore.entity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.singularcover.videoRentalStore.entity.Film;

@Repository
public interface FilmRepository extends CrudRepository<Film, Long> {

	public List<Film> findByTypeIdTypeFilm(Long idType);

}
