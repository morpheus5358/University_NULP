package com.example.lab4.hotel.managment.controller;

import com.example.lab4.hotel.managment.entity.amenity.Amenity;
import com.example.lab4.hotel.managment.service.AmenityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/apartment/amenity")
@Slf4j
public class AmenityController {

	private final AmenityService amenityService;

	@GetMapping()
	public ResponseEntity<List<Amenity>> getAllAmenities() {
		List<Amenity> amenities = amenityService.getAllAmenities();
		if (amenities.isEmpty()) {
			log.warn("No amenities found");
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(amenities);
	}
}