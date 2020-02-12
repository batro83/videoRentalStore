package com.singularcover.videoRentalStore.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.singularcover.videoRentalStore.entity.Rent;
import com.singularcover.videoRentalStore.service.SurchargesService;

/**
 * Surcharges business logic service
 * 
 * @author roger
 *
 */
@Service
public class SurchargesServiceImpl implements SurchargesService {

	@Override
	public int calculateSurcharges(List<Rent> rentList) {
		return rentList.stream().mapToInt(rent -> calculateSurcharges(rent)).sum();
	}

	private int calculateSurcharges(Rent rent) {
		int surcharge = 0;
		long startTime = rent.getDateRent().getTime();
		long endTime = Calendar.getInstance().getTimeInMillis();
		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);

		if (diffDays > rent.getDays()) {
			int days = (int) (diffDays - rent.getDays());
			surcharge = (int) days * rent.getFilm().getType().getPrice();
		}
		return surcharge;
	}
}