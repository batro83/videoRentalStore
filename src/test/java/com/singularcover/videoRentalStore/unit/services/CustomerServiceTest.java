package com.singularcover.videoRentalStore.unit.services;


import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.singularcover.videoRentalStore.entity.Customer;
import com.singularcover.videoRentalStore.entity.repository.CustomerRepository;
import com.singularcover.videoRentalStore.service.impl.CustomerServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Mock
	CustomerRepository customerRepository;
	
	@InjectMocks
	CustomerServiceImpl customerService;
	
	@Test
	public void findCustomerByIdTest_OK() {
		Customer dummyCustomer = new Customer();
		dummyCustomer.setIdCustomer(1L);
		Optional<Customer> dummyCustomerOptional= Optional.ofNullable(dummyCustomer);
		
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(dummyCustomerOptional);

		Customer customer = customerService.findCustomerById(1l);
		
		Assert.assertNotNull(customer);
	}
	
	
	@Test
	public void findCustomerByIdTest_KO() {
		Optional<Customer> dummyCustomerOptional= Optional.ofNullable(null);
		
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(dummyCustomerOptional);

		Customer customer = customerService.findCustomerById(1l);
		
		Assert.assertNull(customer);
	}


}
