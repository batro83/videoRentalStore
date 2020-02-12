package com.singularcover.videoRentalStore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singularcover.videoRentalStore.entity.Customer;
import com.singularcover.videoRentalStore.entity.repository.CustomerRepository;
import com.singularcover.videoRentalStore.service.CustomerService;

/**
 * Business logic for customers
 * 
 * @author roger
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer findCustomerById(Long id) throws Exception {
		return customerRepository.findById(id).orElseThrow(() -> new Exception("USER_NOT_EXIST"));
	}
}