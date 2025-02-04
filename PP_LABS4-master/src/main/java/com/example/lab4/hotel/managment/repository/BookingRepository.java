package com.example.lab4.hotel.managment.repository;

import com.example.lab4.hotel.managment.entity.Booking;
import com.example.lab4.hotel.managment.entity.apartment.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	boolean existsByApartment(Apartment apartment);

	List<Booking> findByApartment(Apartment apartment);

	boolean existsByApartmentAndStartDateBetween(Apartment apartment, LocalDate startDate, LocalDate endDate);
}