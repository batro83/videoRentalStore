package com.singularcover.videoRentalStore.controllers;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Collection;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.service.impl.InventoryServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class InventoryController {

	private static final Logger LOG = getLogger(InventoryController.class);

	@Autowired
	private InventoryServiceImpl inventoryService;

	// TODO: put paths into an other class

	@ApiOperation(value = "Get inventory", notes = "All movies")
	@GetMapping(path = "/inventory")
	public ResponseEntity<Collection<Film>> findAllMovies() {
		LOG.debug("StoreController - findAllMovies");
		return ResponseEntity.ok(inventoryService.getAllMovies());
	}

	@ApiOperation(value = "Get inventory by type id", notes = "Movies by type id")
	@GetMapping(path = "/inventory/{type}")
	public ResponseEntity<Collection<Film>> findMoviesByType(@PathVariable(value = "type", required = true) Long type) {
		LOG.debug("StoreController - findMoviesByType");
		return ResponseEntity.ok(inventoryService.getMoviesByType(type));
	}

	@ApiOperation(value = "Remove movie by id", notes = "Remove movie by id from inventory")
	@DeleteMapping(path = "/inventory/{idFilm}")
	public ResponseEntity<HttpStatus> removeFilm(@PathVariable(value = "idFilm", required = true) Long idFilm) {
		LOG.debug("StoreController - removeFilm");
		inventoryService.deleteFilm(idFilm);
		return ResponseEntity.ok(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Add movie", notes = "Add movie in inventory")
	@PostMapping(path = "/inventory")
	public ResponseEntity<HttpStatus> addFilm(@RequestBody(required = true) Film film) {
		LOG.debug("StoreController - addFilm");
		inventoryService.saveOrUpdateFilm(film);
		return ResponseEntity.ok(HttpStatus.NO_CONTENT);
	}
}