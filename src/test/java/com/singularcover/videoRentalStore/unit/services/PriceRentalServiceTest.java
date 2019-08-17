package com.singularcover.videoRentalStore.unit.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.entity.TypeFilm;
import com.singularcover.videoRentalStore.service.impl.PriceRentalServiceImpl;
import com.singularcover.videoRentalStore.utils.TypeFilmCts;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceRentalServiceTest {
	
	
	private final int premium_price = 3;
	private final int basic_price = 1;
	
	@Autowired
	private PriceRentalServiceImpl priceRentalService;
	
	
	@Test
	public void calculateRentalPriceNewReleases_OK() {
		final int daysForRent = 10;	
		TypeFilm typeFilm = TypeFilm.builder().
				setPrice(premium_price).
				setIdTypeFilm(new Long(TypeFilmCts.NEW_RELEASES))
						.build();
		
		Film dummyFilm = Film.builder()
				.setType(typeFilm)
				.setIdFilm(1L)
				.build();
				
		int price = priceRentalService.calculateRentalPrice(dummyFilm, daysForRent);		
		Assert.assertEquals(30, price);

	}
	
	@Test
	public void calculateRentalPriceRegularFilms_OK() {
		final int daysForRent = 10;	
		TypeFilm typeFilm = TypeFilm.builder().
				setPrice(basic_price).
				setIdTypeFilm(new Long(TypeFilmCts.REGULAR_FILMS))
						.build();
		
		Film dummyFilm = Film.builder()
				.setType(typeFilm)
				.setIdFilm(1L)
				.build();
			
		int price = priceRentalService.calculateRentalPrice(dummyFilm, daysForRent);		
		Assert.assertEquals(8, price);

	}
	
	@Test
	public void calculateRentalPriceOldFilms_OK() {
		final int daysForRent = 10;	
		
		TypeFilm typeFilm = TypeFilm.builder().
				setPrice(basic_price).
				setIdTypeFilm(new Long(TypeFilmCts.OLD_FILMS))
						.build();
		
		Film dummyFilm = Film.builder()
				.setType(typeFilm)
				.setIdFilm(1L)
				.build();
		
		int price = priceRentalService.calculateRentalPrice(dummyFilm, daysForRent);		
		Assert.assertEquals(6, price);

	}


}
