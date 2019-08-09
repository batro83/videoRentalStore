package com.singularcover.videoRentalStore.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.service.FilmService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
public class StoreController {
		
	private static final Logger logger = LogManager.getLogger(StoreController.class);
	
	@Autowired
	private FilmService filmService;
	
	
	@ApiOperation(value = "Get inventory", notes = "All movies")
	@GetMapping(path = "/inventory")
    public ResponseEntity<List<Film>> getAllMovies() {		
        return ResponseEntity.ok(filmService.getAllMovies());
    }
	
	
	@ApiOperation(value = "Get inventory by type", notes = "Movies by type")
	@PostMapping(path = "/inventory/{type}")
    public ResponseEntity<HttpStatus> getMoviesByType(@PathVariable(value = "type", required = true) String type) {
		
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

	
	@ApiOperation(value = "Renting films", notes = "Renting one or several films and calculating the price")
	@PostMapping(path = "/rent/{command}")
    public ResponseEntity<HttpStatus> rentingFilms(@PathVariable("command") String command) {
		
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }


}
