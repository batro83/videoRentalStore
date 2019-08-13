package com.singularcover.videoRentalStore.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.singularcover.videoRentalStore.entity.Rent;
import com.singularcover.videoRentalStore.utils.TypeFilmCts;

/**
 * Surcharges business logic service
 * @author roger
 *
 */
@Service
public class SurchargesService {
		

	public int calculateSurcharges(List<Rent> rentList) {
		int price = 0;
		for (Rent rent : rentList) {
			price += calculateSurcharges(rent);
		}
		return price;
	}


	public int calculateSurcharges(Rent rent) {
		int price = 0;
		switch (rent.getFilm().getType().getIdTypeFilm().intValue()) {
		case TypeFilmCts.NEW_RELEASES:
			price = surcharges(rent);
			break;
		case TypeFilmCts.REGULAR_FILMS:
			price = surcharges(rent);
			break;
		case TypeFilmCts.OLD_FILMS:
			price = surcharges(rent);
			break;
			
		default:
			break;
		}
		
		return price;
	}
		
	
	private int surcharges(Rent rent) {
		int surcharge = 0;
		long startTime = rent.getDateRent().getTime();
		long endTime = Calendar.getInstance().getTimeInMillis();
		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
		
		if(diffDays > rent.getDays()) {
			int days = (int) (diffDays - rent.getDays());
			surcharge = (int) days * rent.getFilm().getType().getPrice();
		}
		
		return surcharge;
	}
	

}
