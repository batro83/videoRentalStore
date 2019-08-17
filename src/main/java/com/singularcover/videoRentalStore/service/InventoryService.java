package com.singularcover.videoRentalStore.service;

import java.util.List;

import com.singularcover.videoRentalStore.entity.Film;

public interface InventoryService {

	public List<Film> getAllMovies();
	
	public List<Film> getMoviesByType(Long type);
	
	public List<Film> getMoviesByIdList(List<Long> idList);
	
	public void deleteFilm(Long id);
	
	public Film saveOrUpdateFilm(Film film);
		
}
