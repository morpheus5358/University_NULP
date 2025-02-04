package com.example.lab4.hotel.managment.service.impl;

import com.example.lab4.hotel.managment.entity.amenity.Amenity;
import com.example.lab4.hotel.managment.repository.AmenityRepository;
import com.example.lab4.hotel.managment.service.AmenityService;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AmenityServiceImpl implements AmenityService {

	private final AmenityRepository amenityRepository;

	@Override
	@Cacheable("amenities")
	public List<Amenity> getAllAmenities() {
		return amenityRepository.findAll();
	}

	@Override
	public Optional<Amenity> findById(Long amenityId) {
		return amenityRepository.findById(amenityId);
	}
}
