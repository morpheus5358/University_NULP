package com.example.lab4.hotel.managment.service;

import com.example.lab4.hotel.managment.entity.Booking;
import com.example.lab4.hotel.managment.entity.apartment.Apartment;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

	Booking bookApartment(String email, Long apartmentId, LocalDate startDate, LocalDate endDate);

	boolean isDateAvailable(Apartment apartment, LocalDate date);

	List<Booking> getBookingHistoryForApartment(Apartment apartment);

	boolean existsByApartment(Apartment apartment);

	void unBookApartment(Long bookingId);
}
