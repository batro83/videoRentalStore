package com.singularcover.videoRentalStore.unit.controller;

import java.util.Arrays;
import java.util.List;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.singularcover.videoRentalStore.controllers.InventoryController;
import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.service.InventoryService;

@RunWith(SpringRunner.class)
@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private InventoryService inventoryService;
	
	@Autowired
	private ObjectMapper mapper;

	@Test
	public void findAllMoviesTest_OK() throws Exception {
				
		Film dummyFilm = Film.builder()
				.setIdFilm(1L)
				.setName("FilmName")
				.build();
		
		Film dummyFilm2 = Film.builder()
				.setIdFilm(2L)
				.setName("FilmName2")
				.build();
		
		Mockito.when(inventoryService.getAllMovies())
			.thenReturn(Arrays.asList(dummyFilm, dummyFilm2));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/inventory")
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())				
				.andReturn();

		Assert.assertNotNull(result.getResponse());		
				
		List<Film> list = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Film>>() {});
		Assert.assertEquals(2, list.size());
		Assert.assertEquals("FilmName", list.get(0).getName());
		Assert.assertEquals(1l, list.get(0).getIdFilm().longValue());
		Assert.assertEquals("FilmName2", list.get(1).getName());
		Assert.assertEquals(2l, list.get(1).getIdFilm().longValue());
	}
	
	
	@Test
	public void findMoviesByTypeTest_OK() throws Exception {
		Film dummyFilm = Film.builder()
				.setIdFilm(1L)
				.setName("FilmName")
				.build();
		
		Mockito.when(inventoryService.getMoviesByType(3L))
			.thenReturn(Arrays.asList(dummyFilm));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/inventory/{type}", 3)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		Assert.assertNotNull(result.getResponse());
		
		List<Film> list = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Film>>() {});
		Assert.assertEquals(1, list.size());
		Assert.assertEquals("FilmName", list.get(0).getName());
		Assert.assertEquals(1l, list.get(0).getIdFilm().longValue());
		
	}
	
	@Test
	public void removeFilmTest_OK() throws Exception {		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/inventory/{idFilm}", 1)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		Assert.assertNotNull(result.getResponse());
		
	}
	
	
	@Test
	public void addFilmTest_OK() throws Exception {		
		
		Film dummyFilm = Film.builder()
				.setIdFilm(1L)
				.setName("FilmName")
				.build();
		
	    Gson gson = new Gson();
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/inventory")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(dummyFilm))
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		Assert.assertNotNull(result.getResponse());
		
	}
		
}
