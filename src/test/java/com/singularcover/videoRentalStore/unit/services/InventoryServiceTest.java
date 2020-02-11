package com.singularcover.videoRentalStore.unit.services;

import static org.assertj.core.util.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.entity.repository.FilmRepository;
import com.singularcover.videoRentalStore.service.impl.InventoryServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceTest {
	
	@InjectMocks
	InventoryServiceImpl inventoryService;
	
	@Mock
	FilmRepository filmRepository;
	
	@Test
	public void deleteFilmTest_OK() {
		Film dummyFilm = Film.builder()
				.setIdFilm(1L)
				.build();

		// Act
		inventoryService.deleteFilm(1L);
		// Assert
		verify(filmRepository, times(1)).deleteById(dummyFilm.getIdFilm());
	}

	@Test
	public void saveOrUpdateFilm_OK() {
		Film dummyFilm = Film.builder()
				.setIdFilm(1L)
				.build();

		when(filmRepository.save(any(Film.class))).thenReturn(dummyFilm);

		Film film = inventoryService.saveOrUpdateFilm(dummyFilm);
		assertEquals(film.getIdFilm(), dummyFilm.getIdFilm());

	}

	@Test
	public void getAllMovies_OK() {
		when(filmRepository.findAll()).thenReturn(newArrayList(Film.builder().build()));

		List<Film> filmList = inventoryService.getAllMovies();
		assertNotNull(filmList);
		assertTrue(filmList.size() > 0);

	}
}