package com.singularcover.videoRentalStore.unit.controller;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.singularcover.videoRentalStore.controllers.InventoryController;
import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.service.impl.InventoryServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InventoryServiceImpl inventoryService;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void findAllMoviesTest_OK() throws Exception {

		Film dummyFilm = Film.builder().setIdFilm(1L).setName("FilmName").build();
		Film dummyFilm2 = Film.builder().setIdFilm(2L).setName("FilmName2").build();
		when(inventoryService.getAllMovies()).thenReturn(asList(dummyFilm, dummyFilm2));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/inventory").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		assertNotNull(result.getResponse());

		List<Film> list = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Film>>() {
		});
		assertEquals(2, list.size());
		assertEquals("FilmName", list.get(0).getName());
		assertEquals(1l, list.get(0).getIdFilm().longValue());
		assertEquals("FilmName2", list.get(1).getName());
		assertEquals(2l, list.get(1).getIdFilm().longValue());
	}

	@Test
	public void findMoviesByTypeTest_OK() throws Exception {
		Film dummyFilm = Film.builder().setIdFilm(1L).setName("FilmName").build();
		when(inventoryService.getMoviesByType(3L)).thenReturn(asList(dummyFilm));

		RequestBuilder requestBuilder = get("/api/inventory/{type}", 3).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		assertNotNull(result.getResponse());

		List<Film> list = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Film>>() {
		});
		assertEquals(1, list.size());
		assertEquals("FilmName", list.get(0).getName());
		assertEquals(1l, list.get(0).getIdFilm().longValue());
	}

	@Test
	public void removeFilmTest_OK() throws Exception {
		RequestBuilder requestBuilder = delete("/api/inventory/{idFilm}", 1).accept(APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void addFilmTest_OK() throws Exception {
		Film dummyFilm = Film.builder().setIdFilm(1L).setName("FilmName").build();
		Gson gson = new Gson();

		RequestBuilder requestBuilder = post("/api/inventory").contentType(APPLICATION_JSON)
				.content(gson.toJson(dummyFilm))
				.accept(APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		assertNotNull(result.getResponse());
	}
}