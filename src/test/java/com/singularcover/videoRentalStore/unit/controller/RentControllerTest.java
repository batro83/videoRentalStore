package com.singularcover.videoRentalStore.unit.controller;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

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
		final String filmsToRent = asList(1L, 2L).stream().map(Object::toString).collect(joining(","));
		final Integer days = 10;
		final String path = "/api/{customer}/rent/{films}/{days}";

		Customer c = new Customer(1L);

		when(customerService.findCustomerById(1L)).thenReturn(c);

		when(rentService.rentFilms(anyList(), any(), anyInt()))
				.thenReturn(new RentalDTO(10, 1));

		RequestBuilder requestBuilder = post(path, idCustomer, filmsToRent, days).accept(APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

		assertNotNull(result.getResponse());

		RentalDTO resp = mapper.readValue(result.getResponse().getContentAsString(), RentalDTO.class);
		assertNotNull(resp);
		assertEquals(10, resp.getPrice().intValue());
		assertEquals(1, resp.getPoints().intValue());
	}

	@Test
	public void returnFilmsTest_OK() throws Exception {
		final Long idCustomer = 1L;
		final String filmsToRent = asList(1L, 2L).stream().map(Object::toString).collect(joining(","));
		final Integer days = 10;
		final String path = "/api/{customer}/return/{films}";

		Customer c = new Customer(1L);

		when(customerService.findCustomerById(1L)).thenReturn(c);

		when(rentService.returnFilms(anyList(), any())).thenReturn(new RentalReturnDTO(15));

		RequestBuilder requestBuilder = post(path, idCustomer, filmsToRent, days).accept(APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

		assertNotNull(result.getResponse());

		RentalReturnDTO resp = mapper.readValue(result.getResponse().getContentAsString(), RentalReturnDTO.class);

		assertNotNull(resp);
		assertEquals(15, resp.getSurcharges().intValue());
	}
}