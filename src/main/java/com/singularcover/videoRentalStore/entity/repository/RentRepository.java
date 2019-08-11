package com.singularcover.videoRentalStore.entity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.singularcover.videoRentalStore.entity.Rent;

@Repository
public interface RentRepository extends CrudRepository<Rent, Integer> {

}
