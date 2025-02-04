package com.example.lab4.hotel.managment.service;

import com.example.lab4.hotel.managment.dto.request.HotelRequestDTO;
import com.example.lab4.hotel.managment.entity.apartment.Hotel;

import java.util.List;

public interface HotelService {

	Hotel findHotelById(Long id);

	List<Hotel> getAllHotels();

	Hotel createHotel(HotelRequestDTO hotelRequestDTO);

	void deleteHotel(Long id);

	Hotel updateHotel(HotelRequestDTO hotelRequestDTO, Long id);

	void save(Hotel hotel);
}
