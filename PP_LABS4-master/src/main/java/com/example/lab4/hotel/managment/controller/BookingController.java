package com.example.lab4.hotel.managment.controller;

import com.example.lab4.hotel.managment.dto.request.BookingRequestDTO;
import com.example.lab4.hotel.managment.entity.Booking;
import com.example.lab4.hotel.managment.entity.apartment.Apartment;
import com.example.lab4.hotel.managment.service.ApartmentService;
import com.example.lab4.hotel.managment.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

	private final BookingService bookingService;
	private final ApartmentService apartmentService;

	@PostMapping()
	public ResponseEntity<Booking> bookApartment(@Valid @RequestBody BookingRequestDTO bookingRequest) {
		Booking booking = bookingService.bookApartment(
				bookingRequest.getEmail(),
				bookingRequest.getApartmentId(),
				bookingRequest.getStartDate(),
				bookingRequest.getEndDate());
		return ResponseEntity.ok(booking);
	}

	@DeleteMapping("/{bookingId}")
	public ResponseEntity<Void> unBookApartment(@PathVariable Long bookingId) {
		bookingService.unBookApartment(bookingId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/availability/{apartmentId}/{date}")
	public ResponseEntity<Boolean> isDateAvailable(@PathVariable Long apartmentId, @PathVariable LocalDate date) {
		Apartment apartment = apartmentService.findApartmentById(apartmentId);
		boolean available = bookingService.isDateAvailable(apartment, date);
		return ResponseEntity.ok(available);
	}

	@GetMapping("/history/{apartmentId}")
	public ResponseEntity<List<Booking>> getBookingHistory(@PathVariable Long apartmentId) {
		Apartment apartment = apartmentService.findApartmentById(apartmentId);
		List<Booking> bookingHistory = bookingService.getBookingHistoryForApartment(apartment);
		if (bookingHistory.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(bookingHistory);
	}
}