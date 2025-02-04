package com.example.lab4.hotel.managment.controller;

import com.example.lab4.hotel.managment.entity.amenity.AmenityType;
import com.example.lab4.hotel.managment.entity.apartment.Apartment;
import com.example.lab4.hotel.managment.entity.apartment.Type;
import com.example.lab4.hotel.managment.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apartment")
@RequiredArgsConstructor
public class ApartmentController {

	private final ApartmentService apartmentService;

	@GetMapping()
	public ResponseEntity<List<Apartment>> searchApartments(
			@RequestParam(required = false) AmenityType amenityType,
			@RequestParam(required = false) Type type) {
		List<Apartment> apartments = apartmentService.searchApartment(amenityType, type);
		if (apartments.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(apartments);
	}

	@GetMapping("/statistics/{id}")
	public ResponseEntity<String> getStatistics(@PathVariable Long id) {
		String statistics = apartmentService.getStatistics(id);
		return ResponseEntity.ok(statistics);
	}

}