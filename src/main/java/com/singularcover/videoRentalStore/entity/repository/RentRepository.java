package com.singularcover.videoRentalStore.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.singularcover.videoRentalStore.entity.Rent;

@Repository
public interface RentRepository extends CrudRepository<Rent, Integer> {

	public List<Rent> findByFilmIdFilmIn(List<Long> idFilm);

	public List<Rent> findByFilmIdFilmInAndCustomerIdCustomer(List<Long> idFilm, Long idCustomer);

	@Query("SELECT r FROM Rent r WHERE r.customer.idCustomer = :idCustomer AND r.film.idFilm IN :idFilm AND r.dateReturn is null")
	public List<Rent> findRentedFilms(@Param("idFilm") List<Long> filmList, @Param("idCustomer") Long idCustomer);
}
