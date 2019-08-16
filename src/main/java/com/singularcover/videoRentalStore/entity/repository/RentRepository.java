package com.singularcover.videoRentalStore.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.singularcover.videoRentalStore.entity.Rent;

@Repository
public interface RentRepository extends CrudRepository<Rent, Integer> {

	
	public List<Rent> findByFilmIdFilmIn(List<Long> idType);
	
	public List<Rent> findByFilmIdFilmInAndCustomerIdCustomer(List<Long> idType, Long idCustomer);
	
	@Query("SELECT r FROM Rent r WHERE r.customer.idCustomer = :idCustomer AND r.film.idFilm IN :idType AND r.dateReturn is null")
	public List<Rent> findRentedFilms(@Param("idType") List<Long> idType, @Param("idCustomer") Long idCustomer);
}
