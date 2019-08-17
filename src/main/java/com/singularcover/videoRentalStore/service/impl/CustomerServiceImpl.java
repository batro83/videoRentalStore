package com.singularcover.videoRentalStore.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singularcover.videoRentalStore.entity.Customer;
import com.singularcover.videoRentalStore.entity.repository.CustomerRepository;
import com.singularcover.videoRentalStore.service.CustomerService;

/**
 * Business logic for customers
 * @author roger
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer findCustomerById(Long id) {
		Customer c = null;
		Optional<Customer> d = customerRepository.findById(id);
		
		if(d.isPresent())
			c = d.get();
		
		return c;
	}

}
