package com.singularcover.videoRentalStore.unit.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.entity.repository.FilmRepository;
import com.singularcover.videoRentalStore.service.InventoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceTest {
	
	@InjectMocks
	InventoryService inventoryService;
	
	@Mock
	FilmRepository filmRepository;
	
	@Test
	public void deleteFilmTest_OK() {
		Film dummyFilm = new Film();
		dummyFilm.setIdFilm(1L);
		
		// Act
		inventoryService.deleteFilm(1L);
        // Assert
        Mockito.verify(filmRepository,  Mockito.times(1)).deleteById(dummyFilm.getIdFilm());
				
	}
	
	
	@Test
	public void saveOrUpdateFilm_OK() {
		Film dummyFilm = new Film();
		dummyFilm.setIdFilm(1L);
		
		Mockito.when(filmRepository.save(Mockito.any(Film.class))).thenReturn(dummyFilm);
		
		Film film = inventoryService.saveOrUpdateFilm(dummyFilm);
		Assert.assertEquals(film.getIdFilm(), dummyFilm.getIdFilm());
				
	}
	

}
