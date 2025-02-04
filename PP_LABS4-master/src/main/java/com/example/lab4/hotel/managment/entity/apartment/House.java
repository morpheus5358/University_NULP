package com.example.lab4.hotel.managment.entity.apartment;

import com.example.lab4.hotel.managment.entity.amenity.Amenity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "houses")
@AllArgsConstructor
public class House extends ApartmentCapacity {

	public House(String name, BigDecimal price, Type type, List<Amenity> amenities, int maxSpace) {
		super(name, price, type, amenities, maxSpace);
	}
}
