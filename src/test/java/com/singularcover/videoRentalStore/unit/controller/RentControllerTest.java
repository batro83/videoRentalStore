package com.singularcover.videoRentalStore.unit.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.singularcover.videoRentalStore.controllers.RentController;
import com.singularcover.videoRentalStore.dto.RentalDTO;
import com.singularcover.videoRentalStore.dto.RentalReturnDTO;
import com.singularcover.videoRentalStore.entity.Customer;
import com.singularcover.videoRentalStore.service.impl.CustomerServiceImpl;
import com.singularcover.videoRentalStore.service.impl.RentServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(RentController.class)
public class RentControllerTest {

	@MockBean
	private RentServiceImpl rentService;
	
	@MockBean
	private CustomerServiceImpl customerService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void rentFilmTest_OK() throws Exception {
		final Long idCustomer = 1L;
    	final String filmsToRent = Arrays.asList(1L, 2L).stream().map(Object::toString).collect(Collectors.joining(","));;
    	final Integer days = 10;
    	final String path = "/api/{customer}/rent/{films}/{days}";
    		
		Customer c = new Customer(1L);
		
		Mockito.when(customerService.findCustomerById(1L))
			.thenReturn(c);
		
		Mockito.when(rentService.rentFilms(Mockito.anyList(), Mockito.any(), Mockito.anyInt()))
			.thenReturn(new RentalDTO(10, 1));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(path, idCustomer, filmsToRent, days)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())				
				.andReturn();

		Assert.assertNotNull(result.getResponse());		
				
		RentalDTO resp = mapper.readValue(result.getResponse().getContentAsString(), RentalDTO.class);
		Assert.assertNotNull(resp);
		Assert.assertEquals(10, resp.getPrice().intValue());
		Assert.assertEquals(1, resp.getPoints().intValue());
	}
	
	
	@Test
	public void returnFilmsTest_OK() throws Exception {
		final Long idCustomer = 1L;
    	final String filmsToRent = Arrays.asList(1L, 2L).stream().map(Object::toString).collect(Collectors.joining(","));;
    	final Integer days = 10;
    	final String path = "/api/{customer}/return/{films}";
    		
		Customer c = new Customer(1L);
		
		Mockito.when(customerService.findCustomerById(1L))
			.thenReturn(c);
		
		Mockito.when(rentService.returnFilms(Mockito.anyList(), Mockito.any()))
			.thenReturn(new RentalReturnDTO(15));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(path, idCustomer, filmsToRent, days)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())				
				.andReturn();

		Assert.assertNotNull(result.getResponse());		
				
		RentalReturnDTO resp = mapper.readValue(result.getResponse().getContentAsString(), RentalReturnDTO.class);
		
		Assert.assertNotNull(resp);
		Assert.assertEquals(15, resp.getSurcharges().intValue());
	}
	
	
}
