package com.example.lab4.hotel.managment.service.impl;


import com.example.lab4.hotel.managment.dto.request.HouseRequestDTO;
import com.example.lab4.hotel.managment.entity.amenity.Amenity;
import com.example.lab4.hotel.managment.entity.apartment.House;
import com.example.lab4.hotel.managment.exception.ApartmentIsBookedException;
import com.example.lab4.hotel.managment.exception.HouseNotFoundException;
import com.example.lab4.hotel.managment.repository.HouseRepository;
import com.example.lab4.hotel.managment.service.HouseService;
import com.example.lab4.hotel.managment.util.AmenityUtil;
import com.example.lab4.hotel.managment.util.UpdaterField;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HouseServiceImpl implements HouseService {

	private final HouseRepository houseRepository;
	private final UpdaterField updaterField;
	private final AmenityUtil amenityUtil;

	@Override
	public House findHouseById(Long id) {
		return houseRepository.findById(id)
				.orElseThrow(() -> new HouseNotFoundException(HttpStatus.NOT_FOUND.value(), "House not found"));
	}

	@Override
	public List<House> getAllHouse() {
		return houseRepository.findAll();
	}

	@Override
	@Transactional
	public House createHouse(HouseRequestDTO houseRequestDTO) {
		List<Amenity> amenities = amenityUtil.getAmenities(houseRequestDTO.getAmenityIds());
		House house = new House(houseRequestDTO.getName(), houseRequestDTO.getPrice(),
				houseRequestDTO.getType(), amenities, houseRequestDTO.getMaxSpace());
		return houseRepository.save(house);
	}

	@Override
	@Transactional
	public void deleteHouse(Long id) {
		House house = findHouseById(id);

		if (amenityUtil.existsByApartment(house)) {
			throw new ApartmentIsBookedException(HttpStatus.BAD_REQUEST.value(), "Cannot delete house because it is linked to a booking.");
		}

		houseRepository.delete(house);
	}

	@Override
	@Transactional
	public House updateHouse(HouseRequestDTO houseRequestDTO, Long id) {
		House house = findHouseById(id);
		List<Amenity> amenities = amenityUtil.getAmenities(houseRequestDTO.getAmenityIds());
		List<Amenity> mutableAmenities = new ArrayList<>(amenities);

		house.updateFields(houseRequestDTO, updaterField, mutableAmenities);
		return houseRepository.save(house);
	}
}
