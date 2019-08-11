package com.singularcover.videoRentalStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singularcover.videoRentalStore.dto.RentalDTO;
import com.singularcover.videoRentalStore.entity.Film;

@Service
public class RentService {
	
	@Autowired
	private InventoryService invService;
	
	
	
	public RentalDTO rentFilms(List<Long> idFilms) {
		
		RentalDTO dto = new RentalDTO();
		
		// Get films
		List<Film> films = invService.getMoviesByIdList(idFilms);
		
		//calculate price
		
		if(!films.isEmpty()) {
			dto.setPrice(calculatePrice(films));
			dto.setPoints(calculatePoints(films));		
			
			//Save
			
		}
		
		return dto;
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
