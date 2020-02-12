package com.singularcover.videoRentalStore.entity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.singularcover.videoRentalStore.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
