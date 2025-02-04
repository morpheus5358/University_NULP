package com.example.lab4.hotel.managment.service;

import com.example.lab4.hotel.managment.dto.request.HouseRequestDTO;
import com.example.lab4.hotel.managment.entity.apartment.House;

import java.util.List;

public interface HouseService {

	House findHouseById(Long id);

	List<House> getAllHouse();

	House createHouse(HouseRequestDTO houseRequestDTO);

	void deleteHouse(Long id);

	House updateHouse(HouseRequestDTO houseRequestDTO, Long id);
}
