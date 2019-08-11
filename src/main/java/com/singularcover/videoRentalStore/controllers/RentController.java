package com.singularcover.videoRentalStore.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.singularcover.videoRentalStore.dto.RentalDTO;
import com.singularcover.videoRentalStore.entity.Customer;
import com.singularcover.videoRentalStore.service.CustomerService;
import com.singularcover.videoRentalStore.service.RentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class RentController {
	
	private static final Logger LOG = LoggerFactory.getLogger(RentController.class);
	
	@Autowired
	private RentService rentService;
	
	@Autowired
	private CustomerService customerService;
	

	@ApiOperation(value = "Renting films", notes = "Renting one or several films and calculating the price")
	@PostMapping(path = "/{customer}/rent/{films}")
	public ResponseEntity<RentalDTO> rentFilms(
			@PathVariable("customer") Integer customer,
			@PathVariable("films") List<Long> films) {
		
		LOG.debug("RentController - rentFilms");
		
		Customer cust = customerService.findCustomerById(customer);
		
		return ResponseEntity.ok(rentService.rentFilms(films, cust));
	}

}
