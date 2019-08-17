package com.singularcover.videoRentalStore.service;

import java.util.List;

import com.singularcover.videoRentalStore.entity.Rent;

public interface SurchargesService {

	public int calculateSurcharges(List<Rent> rentList);
	
	public int calculateSurcharges(Rent rent);
}
