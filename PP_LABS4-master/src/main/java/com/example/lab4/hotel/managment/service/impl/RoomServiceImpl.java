package com.example.lab4.hotel.managment.service.impl;

import com.example.lab4.hotel.managment.dto.request.RoomRequestDTO;
import com.example.lab4.hotel.managment.dto.response.RoomWithHotelAmenitiesDTO;
import com.example.lab4.hotel.managment.entity.amenity.Amenity;
import com.example.lab4.hotel.managment.entity.apartment.Hotel;
import com.example.lab4.hotel.managment.entity.apartment.Room;
import com.example.lab4.hotel.managment.exception.ApartmentIsBookedException;
import com.example.lab4.hotel.managment.exception.RoomNotFoundException;
import com.example.lab4.hotel.managment.repository.RoomRepository;
import com.example.lab4.hotel.managment.service.HotelService;
import com.example.lab4.hotel.managment.service.RoomService;
import com.example.lab4.hotel.managment.util.AmenityUtil;
import com.example.lab4.hotel.managment.util.UpdaterField;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

	private final RoomRepository roomRepository;
	private final HotelService hotelService;
	private final UpdaterField updaterField;
	private final AmenityUtil amenityUtil;

	@Override
	public Room findRoomById(Long id) {
		return roomRepository.findById(id)
				.orElseThrow(() -> new RoomNotFoundException(HttpStatus.NOT_FOUND.value(), "Room not found"));
	}

	@Override
	@Transactional
	public RoomWithHotelAmenitiesDTO findRoomWithHotelById(Long roomId) {
		Room room = findRoomById(roomId);
		Hotel hotel = room.getHotel();
		List<Amenity> hotelAmenities = hotel.getAmenities();

		return RoomWithHotelAmenitiesDTO.builder()
				.room(room)
				.hotelAmenities(hotelAmenities)
				.build();
	}

	@Override
	public List<Room> getAllRoomsInHotel(Long hotelId) {
		Hotel hotel = hotelService.findHotelById(hotelId);
		return hotel.getRooms();
	}

	@Override
	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}

	@Override
	@Transactional
	public Room addRoomToHotel(RoomRequestDTO roomRequestDTO) {
		Hotel hotel = hotelService.findHotelById(roomRequestDTO.getHotelId());
		List<Amenity> amenities = amenityUtil.getAmenities(roomRequestDTO.getAmenityIds());

		Room room = new Room(roomRequestDTO.getName(), roomRequestDTO.getPrice(),
				roomRequestDTO.getType(), amenities, roomRequestDTO.getMaxSpace(), hotel);

		Room savedRoom = roomRepository.save(room);
		hotel.getRooms().add(room);
		hotelService.save(hotel);
		return savedRoom;
	}

	@Override
	@Transactional
	public void deleteRoomFromHotel(Long roomId) {
		Room room = findRoomById(roomId);

		if (amenityUtil.existsByApartment(room)) {
			throw new ApartmentIsBookedException(HttpStatus.BAD_REQUEST.value(), "Cannot delete room because it is linked to a booking.");
		}

		Hotel hotel = room.getHotel();
		hotel.getRooms().remove(room);
		hotelService.save(hotel);
		roomRepository.delete(room);
	}

	@Override
	@Transactional
	public void deleteAllRooms(List<Room> rooms) {
		boolean hasBookedRooms = rooms.stream()
				.anyMatch(amenityUtil::existsByApartment);

		if (hasBookedRooms) {
			throw new ApartmentIsBookedException(HttpStatus.BAD_REQUEST.value(),
					"Cannot delete hotel because one or more rooms are linked to a booking.");
		}

		roomRepository.deleteAll(rooms);
	}

	@Override
	@Transactional
	public Room updateRoomInHotel(Long id, RoomRequestDTO roomRequestDTO) {
		Room room = findRoomById(id);
		List<Amenity> amenities = amenityUtil.getAmenities(roomRequestDTO.getAmenityIds());
		List<Amenity> mutableAmenities = new ArrayList<>(amenities);

		room.updateFields(roomRequestDTO, updaterField, mutableAmenities);
		return roomRepository.save(room);
	}
}
