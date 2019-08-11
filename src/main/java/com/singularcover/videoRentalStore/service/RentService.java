package com.singularcover.videoRentalStore.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.singularcover.videoRentalStore.dto.RentalDTO;
import com.singularcover.videoRentalStore.entity.Customer;
import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.entity.Rent;
import com.singularcover.videoRentalStore.entity.repository.RentRepository;

@Service
@Transactional
public class RentService {
	
	@Autowired
	private InventoryService invService;
	
	@Autowired
	private RentRepository rentRepository;
	
	
	public RentalDTO rentFilms(List<Long> idFilms, Customer customer) {
		
		RentalDTO dto = new RentalDTO();
		
		// Get films
		List<Film> films = invService.getMoviesByIdList(idFilms);
		
		//calculate price
		
		if(!films.isEmpty()) {
			dto.setPrice(calculatePrice(films));
			dto.setPoints(calculatePoints(films));		
			
			//Save			
			saveRentFilmList(films, customer);
			
		}
		
		
		
		return dto;
	}
	
	
	public void saveRentFilmList(List<Film> filmList, Customer customer) {

		for (Film film : filmList) {
			Rent rent = new Rent(customer, film, new Date(Calendar.getInstance().getTimeInMillis()), film.getType().getPoints());
			rentRepository.save(rent);
		}
	}
	
	public Integer calculatePrice(List<Film> films) {		
		return films.stream()
				  .map(x -> x.getType().getPoints())
				  .reduce(0, Integer::sum);
		
	}
	
	
	
	public Integer calculatePoints(List<Film> films) {		
		return films.stream()
				  .map(x -> x.getType().getPoints())
				  .reduce(0, Integer::sum);
		
	}

}
