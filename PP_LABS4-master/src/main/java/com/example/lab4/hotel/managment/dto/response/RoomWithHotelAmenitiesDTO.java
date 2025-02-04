package com.example.lab4.hotel.managment.dto.response;

import com.example.lab4.hotel.managment.entity.amenity.Amenity;
import com.example.lab4.hotel.managment.entity.apartment.Room;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoomWithHotelAmenitiesDTO {
	private Room room;
	private List<Amenity> hotelAmenities;
}
