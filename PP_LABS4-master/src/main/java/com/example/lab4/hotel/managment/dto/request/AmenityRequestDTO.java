package com.example.lab4.hotel.managment.dto.request;

import com.example.lab4.hotel.managment.entity.amenity.AmenityType;
import com.example.lab4.hotel.managment.entity.amenity.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AmenityRequestDTO {

	@NotNull(message = "Amenity type cannot be null")
	private AmenityType amenityType;
	private int additionalSpace;
	private Category category;
}