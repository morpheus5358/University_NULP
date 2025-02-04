package com.example.lab4.hotel.managment.util;

import com.example.lab4.hotel.managment.entity.amenity.Amenity;
import com.example.lab4.hotel.managment.entity.apartment.Apartment;
import com.example.lab4.hotel.managment.exception.AmenityNotExistException;
import com.example.lab4.hotel.managment.service.AmenityService;
import com.example.lab4.hotel.managment.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AmenityUtil {

	private final AmenityService amenityService;
	private final BookingService bookingService;

	public List<Amenity> getAmenities(List<Long> amenityIds) {
		return Optional.ofNullable(amenityIds)
				.orElseGet(Collections::emptyList).stream()
				.map(amenityId -> amenityService.findById(amenityId)
						.orElseThrow(() -> new AmenityNotExistException(HttpStatus.NOT_FOUND.value(),
								"Amenity with ID " + amenityId + " does not exist")))
				.toList();
	}

	public boolean existsByApartment(Apartment apartment) {
		return bookingService.existsByApartment(apartment);
	}
}
