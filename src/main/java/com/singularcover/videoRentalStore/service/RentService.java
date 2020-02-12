package com.singularcover.videoRentalStore.service;

import java.util.List;

import com.singularcover.videoRentalStore.dto.RentalDTO;
import com.singularcover.videoRentalStore.dto.RentalReturnDTO;
import com.singularcover.videoRentalStore.entity.Customer;

public interface RentService {

	public RentalDTO rentFilms(List<Long> idFilms, Customer customer, Integer days);

	public RentalReturnDTO returnFilms(List<Long> idFilms, Customer customer);
}
