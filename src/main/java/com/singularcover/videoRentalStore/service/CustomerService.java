package com.singularcover.videoRentalStore.service;

import com.singularcover.videoRentalStore.entity.Customer;

public interface CustomerService {
	
	public Customer findCustomerById(Long id);
	
}
