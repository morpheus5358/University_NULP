package com.example.lab4.hotel.managment.service.impl;

import com.example.lab4.hotel.managment.entity.Booking;
import com.example.lab4.hotel.managment.entity.Client;
import com.example.lab4.hotel.managment.entity.apartment.Apartment;
import com.example.lab4.hotel.managment.entity.apartment.Hotel;
import com.example.lab4.hotel.managment.exception.ApartmentNotFoundException;
import com.example.lab4.hotel.managment.exception.ApartmentUnavailableForDateRangeException;
import com.example.lab4.hotel.managment.exception.UserBalanceException;
import com.example.lab4.hotel.managment.repository.BookingRepository;
import com.example.lab4.hotel.managment.service.BookingService;
import com.example.lab4.hotel.managment.service.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ApartmentServiceImpl apartmentService;
    private final ClientService clientService;

    @Override
    @Transactional
    public Booking bookApartment(String email, Long apartmentId, LocalDate startDate, LocalDate endDate) {
        Client user = clientService.findUserByEmail(email);
        Apartment apartment = apartmentService.findApartmentById(apartmentId);

        if (apartment instanceof Hotel) {
            throw new IllegalArgumentException("Cannot book a whole hotel");
        }

        if (isApartmentAlreadyBookedInThisRange(apartment, startDate, endDate)) {
            throw new ApartmentUnavailableForDateRangeException(HttpStatus.BAD_REQUEST.value(),
                    "Apartment is already booked in the given date range");
        }

        BigDecimal totalPrice = apartmentService.getTotalPriceOfApartment(apartment, startDate, endDate);
        if (user.getBalance().compareTo(totalPrice) < 0) {
            log.error("User balance is not enough. Total cost: {} and user balance: {}", totalPrice, user.getBalance());
            throw new UserBalanceException(HttpStatus.BAD_REQUEST.value(),
                    "User balance is not enough to book this apartment");
        }

        user.setBalance(user.getBalance().subtract(totalPrice));
        Booking booking = new Booking(user, apartment, startDate, endDate, apartment.getPrice());

        log.info("Saving booking {}", booking);
        Booking savedBooking = bookingRepository.save(booking);

        clientService.withdrawBalance(user.getEmail(), totalPrice);
        return savedBooking;
    }

    private boolean isApartmentAlreadyBookedInThisRange(Apartment apartment, LocalDate startDate, LocalDate endDate) {
        return bookingRepository.existsByApartmentAndStartDateBetween(apartment, startDate, endDate);
    }

    @Override
    public boolean isDateAvailable(Apartment apartment, LocalDate date) {
        List<Booking> bookings = bookingRepository.findByApartment(apartment);

        return bookings.stream()
                .noneMatch(booking -> {
                    LocalDate start = booking.getStartDate();
                    LocalDate end = booking.getEndDate();
                    return !date.isBefore(start) && !date.isAfter(end);
                });
    }

    @Override
    public List<Booking> getBookingHistoryForApartment(Apartment apartment) {
        return bookingRepository.findByApartment(apartment);
    }

    @Override
    public boolean existsByApartment(Apartment apartment) {
        return bookingRepository.existsByApartment(apartment);
    }

    @Override
    @Transactional
    public void unBookApartment(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ApartmentNotFoundException(HttpStatus.NOT_FOUND.value(), "Booking with id " + bookingId + " not found"));
        bookingRepository.delete(booking);
    }
}