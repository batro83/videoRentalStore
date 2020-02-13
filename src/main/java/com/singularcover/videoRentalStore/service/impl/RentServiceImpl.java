package com.singularcover.videoRentalStore.service.impl;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.singularcover.videoRentalStore.dto.RentalDTO;
import com.singularcover.videoRentalStore.dto.RentalReturnDTO;
import com.singularcover.videoRentalStore.entity.Customer;
import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.entity.Rent;
import com.singularcover.videoRentalStore.entity.repository.RentRepository;
import com.singularcover.videoRentalStore.service.RentService;

/**
 * Rental business logic service
 *
 * @author roger
 *
 */
@Service
@Transactional
public class RentServiceImpl implements RentService {

	@Autowired
	private InventoryServiceImpl invService;

	@Autowired
	private RentRepository rentRepository;

	@Autowired
	private PriceRentalServiceImpl priceRentalService;

	@Autowired
	private SurchargesServiceImpl surchargesService;

	@Override
	public RentalDTO rentFilms(List<Long> idFilms, Customer customer, Integer days) {
		return ofNullable(invService.getMoviesByIdList(idFilms)).map(films -> {
			RentalDTO dto = new RentalDTO();
			dto.setPrice(priceRentalService.calculateRentalPrice(films, days));
			dto.setPoints(calculatePoints(films));
			saveRentFilmList(films, customer, days);
			return dto;
		}).orElse(null);
	}

	@Override
	public RentalReturnDTO returnFilms(List<Long> idFilms, Customer customer) {
		RentalReturnDTO dto = new RentalReturnDTO();
		ofNullable(getRentByIdFilmList(idFilms, customer.getIdCustomer())).ifPresent(rents -> {
			dto.setSurcharges(surchargesService.calculateSurcharges(rents));
			saveReturnedFilmList(rents, customer);
		});
		return dto;
	}

	private List<Rent> getRentByIdFilmList(List<Long> idList, Long idCustomer) {
		return rentRepository.findRentedFilms(idList, idCustomer);
	}

	private void saveRentFilmList(List<Film> filmList, Customer customer, Integer days) {
		List<Rent> rentList = filmList.stream().map(film -> {
			return Rent.builder()
					.setCustomer(customer)
					.setFilm(film)
					.setPoints(film.getType().getPoints())
					.setDays(days)
					.setDateRent(new Date(Calendar.getInstance().getTimeInMillis()))
					.build();
		}).collect(toList());
		rentRepository.saveAll(rentList);
	}

	private void saveReturnedFilmList(List<Rent> rents, Customer customer) {
		rents.stream().forEach(rent -> rent.setDateReturn(new Date(Calendar.getInstance().getTimeInMillis())));
		rentRepository.saveAll(rents);
	}

	private Integer calculatePoints(List<Film> films) {
		return films.stream().map(x -> x.getType().getPoints()).reduce(0, Integer::sum);
	}
}