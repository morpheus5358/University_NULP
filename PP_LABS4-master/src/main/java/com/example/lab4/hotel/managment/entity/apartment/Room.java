package com.example.lab4.hotel.managment.entity.apartment;

import com.example.lab4.hotel.managment.entity.amenity.Amenity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room extends ApartmentCapacity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_id")
	@JsonIgnore
	@ToString.Exclude
	private Hotel hotel;

	public Room(String name, BigDecimal price, Type type, List<Amenity> amenities, int maxSpace, Hotel hotel) {
		super(name, price, type, amenities, maxSpace);
		this.hotel = hotel;
	}
}
