package com.singularcover.videoRentalStore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
public class RentController {
	
	
	@ApiOperation(value = "Renting films", notes = "Renting one or several films and calculating the price")
	@PostMapping(path = "/rent/{command}")
    public ResponseEntity<HttpStatus> rentingFilms(@PathVariable("command") String command) {
		
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

}
