package com.singularcover.videoRentalStore.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.singularcover.videoRentalStore.entity.Film;
import com.singularcover.videoRentalStore.service.PriceRentalService;
import com.singularcover.videoRentalStore.utils.PriceCts;
import com.singularcover.videoRentalStore.utils.TypeFilmCts;

/**
 * Price Rental business logic
 * @author roger
 *
 */
@Service
public class PriceRentalServiceImpl implements PriceRentalService {
	
	@Override
	public int calculateRentalPrice(List<Film> filmList, Integer days) {
		int price = 0;
		for (Film film : filmList) {
			price += calculateRentalPrice(film, days);
		}
		return price;
	}
	
	@Override
	public Integer calculateRentalPrice(Film film, Integer days) {			
		
		int price = 0;
		switch (film.getType().getIdTypeFilm().intValue()) {
		case TypeFilmCts.NEW_RELEASES:
			price = premiumPrice(film, days);
			break;
		case TypeFilmCts.REGULAR_FILMS:
			price = basicPrice(film, days, PriceCts.DAYS_REGULAR_FILMS);
			break;
		case TypeFilmCts.OLD_FILMS:
			price = basicPrice(film, days, PriceCts.DAYS_OLD_FILMS);
			break;
			
		default:
			break;
		}
		
		return price;
	}
	
	private int basicPrice(Film film, Integer days, int priceDays) {
		int price = film.getType().getPrice();				
		if(days > priceDays) {
			int diffDays = days - priceDays;
			price += diffDays * film.getType().getPrice();
		}		
		return price;
	}
	
	
	private int premiumPrice(Film film, Integer days) {
		return film.getType().getPrice() * days;
	}
	
	
	
	

}
