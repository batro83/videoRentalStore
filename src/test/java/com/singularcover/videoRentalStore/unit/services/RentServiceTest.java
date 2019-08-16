package com.singularcover.videoRentalStore.unit.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.singularcover.videoRentalStore.dto.RentalDTO;
import com.singularcover.videoRentalStore.dto.RentalReturnDTO;
import com.singularcover.videoRentalStore.entity.Customer;
import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.entity.Rent;
import com.singularcover.videoRentalStore.entity.TypeFilm;
import com.singularcover.videoRentalStore.entity.repository.RentRepository;
import com.singularcover.videoRentalStore.service.InventoryService;
import com.singularcover.videoRentalStore.service.PriceRentalService;
import com.singularcover.videoRentalStore.service.RentService;
import com.singularcover.videoRentalStore.service.SurchargesService;
import com.singularcover.videoRentalStore.utils.TypeFilmCts;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentServiceTest {
	
	private final int premium_price = 3;
	private final int basic_price = 1;
	
	@InjectMocks
	RentService rentService;	
	
	@Mock
	InventoryService invService;
	
	@Mock
	RentRepository rentRepository;
	
	@Mock
	PriceRentalService priceRentalService;
	
	@Mock
	SurchargesService surchargesService;
	
	
	@Test
	public void rentFilmsPointsTest_OK() {
		
		final int daysForRent = 10;		
		final List<Film> list = createFilmList();
							
		Mockito.when(invService.getMoviesByIdList(Mockito.anyList()))
			.thenReturn(list);
		
		Mockito.when(priceRentalService.calculateRentalPrice(Mockito.anyList(), Mockito.anyInt()))
			.thenReturn(2);
		
		Mockito.when(rentRepository.saveAll(Mockito.anyList()))
			.thenReturn(Arrays.asList(Rent.builder().build()));
		
		RentalDTO dto = rentService.rentFilms(Arrays.asList(1L,2L), new Customer(), daysForRent);
		Assert.assertNotNull(dto);
		Assert.assertEquals(3, dto.getPoints().intValue());
	}
	
	
	@Test
	public void rentFilmsPointsNotExistFilmsTest_OK() {
		
		final int daysForRent = 10;		
		final List<Film> list = new ArrayList<>();
							
		Mockito.when(invService.getMoviesByIdList(Mockito.anyList()))
			.thenReturn(list);
					
		RentalDTO dto = rentService.rentFilms(Arrays.asList(1L,2L), new Customer(), daysForRent);
		Assert.assertNotNull(dto);
		Assert.assertNull(dto.getPoints());
		Assert.assertNull(dto.getPrice());
	}
	
	
	@Test
	public void returnFilmsTest_OK() {
						
		Mockito.when(rentRepository.findByFilmIdFilmIn(Mockito.anyList()))
			.thenReturn(Arrays.asList(Rent.builder().setIdRent(1).build()));
		
		Mockito.when(surchargesService.calculateSurcharges(Mockito.anyList()))
			.thenReturn(1);		
		
		Mockito.when(rentRepository.saveAll(Mockito.anyList()))
			.thenReturn(Arrays.asList(Rent.builder().build()));
		
		RentalReturnDTO dto = rentService.returnFilms(Arrays.asList(1L,2L), new Customer());
		Assert.assertNotNull(dto);
		Assert.assertNotNull(dto.getSurcharges());
	}
	
	
	@Test
	public void returnNotExistFilmsTest_OK() {
		
		final List<Rent> list = new ArrayList<>();
							
		Mockito.when(rentRepository.findByFilmIdFilmIn(Mockito.anyList()))
			.thenReturn(list);
					
		RentalReturnDTO dto = rentService.returnFilms(Arrays.asList(1L,2L), new Customer());
		Assert.assertNotNull(dto);
		Assert.assertNull(dto.getSurcharges());
	}
	
	
	private List<Film> createFilmList(){
		List<Film> list = new ArrayList<>();
		
		TypeFilm typeFilm = TypeFilm.builder().
				setPrice(premium_price).
				setPoints(2).
				setIdTypeFilm(new Long(TypeFilmCts.NEW_RELEASES))
						.build();
		
		Film dummyFilm = Film.builder()
				.setType(typeFilm)
				.setIdFilm(1L)
				.setName("Name1")
				.build();
		
		list.add(dummyFilm);
		
		typeFilm = TypeFilm.builder().
				setPrice(basic_price).
				setPoints(1).
				setIdTypeFilm(new Long(TypeFilmCts.REGULAR_FILMS))
						.build();
		
		dummyFilm = Film.builder()
				.setType(typeFilm)
				.setIdFilm(2L)
				.setName("Name2")
				.build();
		
		list.add(dummyFilm);
		
		return list;
	}

}
