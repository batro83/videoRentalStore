package com.singularcover.videoRentalStore.unit.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.singularcover.videoRentalStore.entity.repository.RentRepository;
import com.singularcover.videoRentalStore.service.InventoryService;
import com.singularcover.videoRentalStore.service.PriceRentalService;
import com.singularcover.videoRentalStore.service.RentService;
import com.singularcover.videoRentalStore.service.SurchargesService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentServiceTest {
	
	@InjectMocks
	RentService rentService;	
	
	@Mock
	InventoryService invService;
	
	@Mock
	RentRepository rentRepository;
	
	@Mock
	PriceRentalService priceRentalService;
	
	@Mock
	SurchargesService surchargesService;
	
	
	@Test
	public void rentFilmsTste_OK() {
		
	}

}
