package com.singularcover.videoRentalStore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.entity.repository.FilmRepository;
import com.singularcover.videoRentalStore.service.InventoryService;

/**
 * Business logic for inventories store
 * 
 * @author roger
 *
 */
@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	FilmRepository filmRepository;

	@Override
	public List<Film> getAllMovies() {
		return (List<Film>) filmRepository.findAll();
	}

	@Override
	public List<Film> getMoviesByType(Long type) {
		return filmRepository.findByTypeIdTypeFilm(type);
	}

	@Override
	public List<Film> getMoviesByIdList(List<Long> idList) {
		return (List<Film>) filmRepository.findAllById(idList);
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