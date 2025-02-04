package com.example.lab4.hotel.managment.service;

import com.example.lab4.hotel.managment.dto.request.RoomRequestDTO;
import com.example.lab4.hotel.managment.dto.response.RoomWithHotelAmenitiesDTO;
import com.example.lab4.hotel.managment.entity.apartment.Room;

import java.util.List;

public interface RoomService {

	Room findRoomById(Long id);

	RoomWithHotelAmenitiesDTO findRoomWithHotelById(Long roomId);

	List<Room> getAllRooms();

	List<Room> getAllRoomsInHotel(Long hotelId);

	Room addRoomToHotel(RoomRequestDTO roomRequestDTO);

	void deleteRoomFromHotel(Long id);

	void deleteAllRooms(List<Room> rooms);

	Room updateRoomInHotel(Long id, RoomRequestDTO roomRequestDTO);

}
