package com.singularcover.videoRentalStore.unit.services;

import static java.util.Arrays.asList;
import static org.assertj.core.util.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.singularcover.videoRentalStore.dto.RentalDTO;
import com.singularcover.videoRentalStore.dto.RentalReturnDTO;
import com.singularcover.videoRentalStore.entity.Customer;
import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.entity.Rent;
import com.singularcover.videoRentalStore.entity.TypeFilm;
import com.singularcover.videoRentalStore.entity.repository.RentRepository;
import com.singularcover.videoRentalStore.service.impl.InventoryServiceImpl;
import com.singularcover.videoRentalStore.service.impl.PriceRentalServiceImpl;
import com.singularcover.videoRentalStore.service.impl.RentServiceImpl;
import com.singularcover.videoRentalStore.service.impl.SurchargesServiceImpl;
import com.singularcover.videoRentalStore.utils.TypeFilmCts;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentServiceTest {

	private final int premium_price = 3;
	private final int basic_price = 1;

	@InjectMocks
	RentServiceImpl rentService;

	@Mock
	InventoryServiceImpl invService;

	@Mock
	RentRepository rentRepository;

	@Mock
	PriceRentalServiceImpl priceRentalService;

	@Mock
	SurchargesServiceImpl surchargesService;

	@Test
	public void rentFilmsPointsTest_OK() {
		final int daysForRent = 10;
		final List<Film> list = createFilmList();

		when(invService.getMoviesByIdList(anyList())).thenReturn(list);
		when(priceRentalService.calculateRentalPrice(anyList(), anyInt())).thenReturn(2);
		when(rentRepository.saveAll(anyList())).thenReturn(asList(Rent.builder().build()));

		RentalDTO dto = rentService.rentFilms(asList(1L, 2L), new Customer(), daysForRent);
		assertNotNull(dto);
		assertEquals(3, dto.getPoints().intValue());
	}

	@Test
	public void rentFilmsPointsNotExistFilmsTest_OK() {
		final int daysForRent = 10;
		when(invService.getMoviesByIdList(anyList())).thenReturn(newArrayList());

		RentalDTO dto = rentService.rentFilms(asList(1L, 2L), new Customer(), daysForRent);
		assertNotNull(dto);
		assertNull(dto.getPoints());
		assertNull(dto.getPrice());
	}

	@Test
	public void returnFilmsTest_OK() {
		when(rentRepository.findRentedFilms(anyList(), anyLong()))
				.thenReturn(asList(Rent.builder().setIdRent(1).build()));
		when(surchargesService.calculateSurcharges(anyList())).thenReturn(1);
		when(rentRepository.saveAll(anyList())).thenReturn(asList(Rent.builder().build()));

		RentalReturnDTO dto = rentService.returnFilms(asList(1L, 2L), new Customer(1L));
		assertNotNull(dto);
		assertNotNull(dto.getSurcharges());
	}

	@Test
	public void returnNotExistFilmsTest_OK() {
		when(rentRepository.findByFilmIdFilmIn(anyList())).thenReturn(newArrayList());

		RentalReturnDTO dto = rentService.returnFilms(asList(1L, 2L), new Customer());
		assertNotNull(dto);
		assertEquals(0, dto.getSurcharges(), 0);
	}

	private List<Film> createFilmList() {
		List<Film> list = new ArrayList<>();

		TypeFilm typeFilm = TypeFilm.builder()
				.setPrice(premium_price)
				.setPoints(2)
				.setIdTypeFilm(new Long(TypeFilmCts.NEW_RELEASES))
				.build();

		Film dummyFilm = Film.builder().setType(typeFilm).setIdFilm(1L).setName("Name1").build();

		list.add(dummyFilm);

		typeFilm = TypeFilm.builder()
				.setPrice(basic_price)
				.setPoints(1)
				.setIdTypeFilm(new Long(TypeFilmCts.REGULAR_FILMS))
				.build();

		dummyFilm = Film.builder().setType(typeFilm).setIdFilm(2L).setName("Name2").build();

		list.add(dummyFilm);

		return list;
	}
}