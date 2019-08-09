package com.singularcover.videoRentalStore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.entity.repository.FilmRepository;

@Service
public class FilmService {
	
	@Autowired
	FilmRepository filmRepository;
	
	public List<Film> getAllMovies() {
		List<Film> films = new ArrayList<>();
		filmRepository.findAll().forEach(film -> films.add(film));
        return films;
	}
	
	
	public List<Film> getMoviesByType(String idType) {
		List<Film> films = new ArrayList<>();
		filmRepository.findByTypeIdTypeFilm(idType).forEach(film -> films.add(film));
        return films;
	}

}
