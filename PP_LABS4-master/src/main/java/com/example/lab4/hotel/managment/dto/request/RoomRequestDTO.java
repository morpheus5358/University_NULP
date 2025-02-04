package com.example.lab4.hotel.managment.dto.request;

import com.example.lab4.hotel.managment.entity.apartment.Type;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RoomRequestDTO implements UpdatableCapacityDTO {

	@NotNull(message = "Hotel ID cannot be null")
	private Long hotelId;

	@NotNull(message = "House name cannot be null")
	@Size(min = 1, max = 255, message = "House name must be between 1 and 255 characters")
	private String name;

	@NotNull(message = "Price cannot be null")
	@Min(value = 0, message = "Price must be positive")
	private BigDecimal price;

	@NotNull(message = "Type cannot be null")
	private Type type;

	@NotNull(message = "Amenities cannot be null")
	private List<Long> amenityIds;


	@Min(value = 1, message = "Max space must be at least 1")
	private int maxSpace;
}
