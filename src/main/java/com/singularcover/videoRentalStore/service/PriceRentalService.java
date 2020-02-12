package com.singularcover.videoRentalStore.service;

import java.util.List;

import com.singularcover.videoRentalStore.entity.Film;

public interface PriceRentalService {

	public int calculateRentalPrice(List<Film> filmList, Integer days);

}
