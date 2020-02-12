package com.singularcover.videoRentalStore.unit.services;

import static org.assertj.core.util.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.entity.Rent;
import com.singularcover.videoRentalStore.entity.TypeFilm;
import com.singularcover.videoRentalStore.service.impl.SurchargesServiceImpl;
import com.singularcover.videoRentalStore.utils.TypeFilmCts;

@RunWith(SpringRunner.class)
public class SurchargesServiceTest {

	private final int premium_price = 3;
	private final int basic_price = 1;

	@InjectMocks
	SurchargesServiceImpl surchargesService;

	@Test
	public void calculateSurchargesNewReleases_OK() {
		Rent rent = createRent(1L, TypeFilmCts.NEW_RELEASES, premium_price);
		int price = surchargesService.calculateSurcharges(newArrayList(rent));
		assertEquals(15, price);
	}

	@Test
	public void calculateSurchargesRegularFilms_OK() {
		Rent rent = createRent(1L, TypeFilmCts.REGULAR_FILMS, basic_price);
		int price = surchargesService.calculateSurcharges(newArrayList(rent));
		assertEquals(5, price);
	}

	@Test
	public void calculateSurchargesOldFilms_OK() {
		Rent rent = createRent(1L, TypeFilmCts.OLD_FILMS, basic_price);
		int price = surchargesService.calculateSurcharges(newArrayList(rent));
		assertEquals(5, price);
	}

	private Rent createRent(Long idFilm, int idTypeFilm, int price) {
		TypeFilm typeFilm = TypeFilm.builder().setPrice(price).setIdTypeFilm(new Long(idTypeFilm)).build();
		Film dummyFilm = Film.builder().setType(typeFilm).setIdFilm(idFilm).build();

		Calendar rentDate = Calendar.getInstance();
		rentDate.setTime(Calendar.getInstance().getTime());
		rentDate.add(Calendar.DAY_OF_YEAR, -10);

		Rent rent = Rent.builder()
				.setFilm(dummyFilm)
				.setDateRent(new Date(rentDate.getTimeInMillis()))
				.setDays(5)
				.build();

		return rent;
	}
}