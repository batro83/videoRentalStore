package com.singularcover.videoRentalStore.unit.services;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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
		Customer dummyCustomer = Customer.builder().setIdCustomer(1L).build();
		when(customerRepository.findById(anyLong())).thenReturn(ofNullable(dummyCustomer));

		Customer customer = customerService.findCustomerById(1l);
		assertNotNull(customer);
	}

	@Test(expected = Exception.class)
	public void findCustomerByIdTest_KO() throws Exception {
		when(customerRepository.findById(anyLong())).thenReturn(empty());
		customerService.findCustomerById(1l);
	}
}