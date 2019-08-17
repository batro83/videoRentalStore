package com.singularcover.videoRentalStore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.entity.repository.FilmRepository;
import com.singularcover.videoRentalStore.service.InventoryService;

/**
 * Business logic for inventories store
 * @author roger
 *
 */
@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	FilmRepository filmRepository;

	@Override
	public List<Film> getAllMovies() {
		List<Film> films = new ArrayList<>();
		filmRepository.findAll().forEach(film -> films.add(film));
		return films;
	}

	@Override
	public List<Film> getMoviesByType(Long type) {
		List<Film> films = new ArrayList<>();
		filmRepository.findByTypeIdTypeFilm(type).forEach(film -> films.add(film));
		return films;
	}

	@Override
	public List<Film> getMoviesByIdList(List<Long> idList) {
		List<Film> films = new ArrayList<>();
		filmRepository.findAllById(idList).forEach(film -> films.add(film));
		return films;
	}

	@Override
	public void deleteFilm(Long id) {
		filmRepository.deleteById(id);
	}

	@Override
	public Film saveOrUpdateFilm(Film film) {
		return filmRepository.save(film);
	}

}
