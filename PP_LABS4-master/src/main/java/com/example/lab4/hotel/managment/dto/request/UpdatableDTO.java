package com.example.lab4.hotel.managment.dto.request;

import com.example.lab4.hotel.managment.entity.apartment.Type;

import java.math.BigDecimal;
import java.util.List;

public interface UpdatableDTO {
	String getName();

	BigDecimal getPrice();

	Type getType();

	List<Long> getAmenityIds();
}

