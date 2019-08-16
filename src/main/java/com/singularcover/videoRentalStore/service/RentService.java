package com.singularcover.videoRentalStore.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.singularcover.videoRentalStore.dto.RentalDTO;
import com.singularcover.videoRentalStore.dto.RentalReturnDTO;
import com.singularcover.videoRentalStore.entity.Customer;
import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.entity.Rent;
import com.singularcover.videoRentalStore.entity.repository.RentRepository;

/**
 * Rental business logic service
 * @author roger
 *
 */
@Service
@Transactional
@Sql({"/data.sql"})
public class RentService {
	
	@Autowired
	private InventoryService invService;
	
	@Autowired
	private RentRepository rentRepository;
	
	@Autowired
	private PriceRentalService priceRentalService;
	
	@Autowired
	private SurchargesService surchargesService;
	
	
	public RentalDTO rentFilms(List<Long> idFilms, Customer customer, Integer days) {

		RentalDTO dto = new RentalDTO();

		// Get films
		List<Film> films = invService.getMoviesByIdList(idFilms);

		if (!films.isEmpty()) {
			// Calculate price and points
			dto.setPrice(priceRentalService.calculateRentalPrice(films, days));
			dto.setPoints(calculatePoints(films));

			// Save rent
			saveRentFilmList(films, customer, days);
		}

		return dto;
	}
	
	public RentalReturnDTO returnFilms(List<Long> idFilms, Customer customer) {

		RentalReturnDTO dto = new RentalReturnDTO();

		// Get rents
		List<Rent> rents = getRentByIdFilmList(idFilms, customer.getIdCustomer());

		if (!rents.isEmpty()) {
			
			dto.setSurcharges(surchargesService.calculateSurcharges(rents));
			// Save rent
			saveReturnedFilmList(rents, customer);
		}

		return dto;
	}
	
	
	private List<Rent> getRentByIdFilmList(List<Long> idList, Long idCustomer) {
		List<Rent> rents = new ArrayList<>();
		rentRepository.findRentedFilms(idList, idCustomer).forEach(rent -> rents.add(rent));
		return rents;
	}
	
	private void saveRentFilmList(List<Film> filmList, Customer customer, Integer days) {
		List<Rent> rentList = new ArrayList<>();
		for (Film film : filmList) {
			Rent rent = new Rent(customer, film, new Date(Calendar.getInstance().getTimeInMillis()),
					film.getType().getPoints(), days);
			rentList.add(rent);			
		}
		rentRepository.saveAll(rentList);
	}
	
	private void saveReturnedFilmList(List<Rent> rents, Customer customer) {
		List<Rent> rentList = new ArrayList<>();
		for (Rent rent : rents) {
			rent.setDateReturn(new Date(Calendar.getInstance().getTimeInMillis()));
			rentList.add(rent);			
		}
		rentRepository.saveAll(rentList);
	}
			
	private Integer calculatePoints(List<Film> films) {		
		return films.stream()
				  .map(x -> x.getType().getPoints())
				  .reduce(0, Integer::sum);		
	}

}
