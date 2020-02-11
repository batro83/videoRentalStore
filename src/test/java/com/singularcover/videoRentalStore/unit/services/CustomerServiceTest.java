package com.singularcover.videoRentalStore.unit.services;


import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
	public void findCustomerByIdTest_OK() throws Exception {
		Customer dummyCustomer = new Customer();
		dummyCustomer.setIdCustomer(1L);
		Optional<Customer> dummyCustomerOptional= ofNullable(dummyCustomer);
		when(customerRepository.findById(anyLong())).thenReturn(dummyCustomerOptional);

		Customer customer = customerService.findCustomerById(1l);
		assertNotNull(customer);
	}
	
	@Test(expected=Exception.class)
	public void findCustomerByIdTest_KO() throws Exception {
		Optional<Customer> dummyCustomerOptional= empty();
		when(customerRepository.findById(anyLong())).thenReturn(dummyCustomerOptional);
		customerService.findCustomerById(1l);
	}
}