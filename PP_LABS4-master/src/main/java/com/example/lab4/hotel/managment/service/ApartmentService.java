package com.example.lab4.hotel.managment.service;

import com.example.lab4.hotel.managment.entity.amenity.AmenityType;
import com.example.lab4.hotel.managment.entity.apartment.Apartment;
import com.example.lab4.hotel.managment.entity.apartment.Type;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ApartmentService {

	List<Apartment> searchApartment(AmenityType amenityType, Type type);

	BigDecimal getTotalPriceOfApartment(Apartment apartment, LocalDate startDate, LocalDate endDate);

	String getStatistics(Long id);

	Apartment findApartmentById(Long apartmentId);

	BigDecimal calculateTotalCostOfApartment(LocalDate startDate, LocalDate endDate, BigDecimal pricePerDay);
}
