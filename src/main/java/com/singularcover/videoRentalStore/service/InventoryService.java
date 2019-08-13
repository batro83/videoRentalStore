package com.singularcover.videoRentalStore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.entity.repository.FilmRepository;

/**
 * Business logic for inventorys store
 * @author roger
 *
 */
@Service
public class InventoryService {

	@Autowired
	FilmRepository filmRepository;

	public List<Film> getAllMovies() {
		List<Film> films = new ArrayList<>();
		filmRepository.findAll().forEach(film -> films.add(film));
		return films;
	}

	public List<Film> getMoviesByType(Long type) {
		List<Film> films = new ArrayList<>();
		filmRepository.findByTypeIdTypeFilm(type).forEach(film -> films.add(film));
		return films;
	}

	public List<Film> getMoviesByIdList(List<Long> idList) {
		List<Film> films = new ArrayList<>();
		filmRepository.findAllById(idList).forEach(film -> films.add(film));
		return films;
	}

	public void deleteFilm(Long id) {
		filmRepository.deleteById(id);
	}

	public void saveOrUpdateFilm(Film film) {
		filmRepository.save(film);
	}

}
