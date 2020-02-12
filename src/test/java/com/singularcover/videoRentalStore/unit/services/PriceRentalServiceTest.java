package com.singularcover.videoRentalStore.unit.services;

import static org.assertj.core.util.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.entity.TypeFilm;
import com.singularcover.videoRentalStore.service.impl.PriceRentalServiceImpl;
import com.singularcover.videoRentalStore.utils.TypeFilmCts;

@RunWith(SpringRunner.class)
public class PriceRentalServiceTest {

	private final int premium_price = 3;
	private final int basic_price = 1;

	@InjectMocks
	private PriceRentalServiceImpl priceRentalService;

	@Test
	public void calculateRentalPriceNewReleases_OK() {
		final int daysForRent = 10;
		TypeFilm typeFilm = TypeFilm.builder()
				.setPrice(premium_price)
				.setIdTypeFilm(new Long(TypeFilmCts.NEW_RELEASES))
				.build();

		Film dummyFilm = Film.builder().setType(typeFilm).setIdFilm(1L).build();

		int price = priceRentalService.calculateRentalPrice(newArrayList(dummyFilm), daysForRent);
		assertEquals(30, price);
	}

	@Test
	public void calculateRentalPriceRegularFilms_OK() {
		final int daysForRent = 10;
		TypeFilm typeFilm = TypeFilm.builder()
				.setPrice(basic_price)
				.setIdTypeFilm(new Long(TypeFilmCts.REGULAR_FILMS))
				.build();

		Film dummyFilm = Film.builder().setType(typeFilm).setIdFilm(1L).build();

		int price = priceRentalService.calculateRentalPrice(newArrayList(dummyFilm), daysForRent);
		assertEquals(8, price);
	}

	@Test
	public void calculateRentalPriceOldFilms_OK() {
		final int daysForRent = 10;

		TypeFilm typeFilm = TypeFilm.builder()
				.setPrice(basic_price)
				.setIdTypeFilm(new Long(TypeFilmCts.OLD_FILMS))
				.build();

		Film dummyFilm = Film.builder().setType(typeFilm).setIdFilm(1L).build();

		int price = priceRentalService.calculateRentalPrice(newArrayList(dummyFilm), daysForRent);
		assertEquals(6, price);
	}
}