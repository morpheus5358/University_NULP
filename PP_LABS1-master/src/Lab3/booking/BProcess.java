package Lab3.booking;

import Lab3.Client.Client;
import Lab3.apartment.Accommodation;
import Lab3.error.ApartmentAlreadyBookedException;
import Lab3.error.ApartmentUnavailableForDateRangeException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BProcess {
    private final Map<Client, Map<Accommodation, LocalDate[]>> bookings = new HashMap<>();
    private final List<Booking> bookingHistory = new ArrayList<>();

    public void bookAccommodation(Client client, Accommodation accommodation, LocalDate startDate, LocalDate endDate) {
        if (bookings.containsKey(client) && bookings.get(client).containsKey(accommodation)) {
            throw new ApartmentAlreadyBookedException("Accommodation already booked for this client");
        }

        if (isAccommodationAlreadyBookedInThisRange(accommodation, startDate, endDate)) {
            throw new ApartmentUnavailableForDateRangeException("Accommodation is already booked in the given date range");
        }

        Map<Accommodation, LocalDate[]> clientBookings = bookings.computeIfAbsent(client, k -> new HashMap<>());
        clientBookings.put(accommodation, new LocalDate[]{startDate, endDate});

        double pricePerDay = accommodation.getPrice();
        Booking booking = new Booking(client, accommodation, startDate, endDate, pricePerDay);
        bookingHistory.add(booking);
    }

    private boolean isAccommodationAlreadyBookedInThisRange(Accommodation accommodation, LocalDate startDate, LocalDate endDate) {
        return bookings.values().stream()
                .filter(clientBookings -> clientBookings.containsKey(accommodation))
                .map(clientBookings -> clientBookings.get(accommodation))
                .anyMatch(bookedDates -> startDate.isBefore(bookedDates[1]) && endDate.isAfter(bookedDates[0]));
    }

    public boolean isDateAvailable(Accommodation accommodation, LocalDate date) {
        return bookings.values().stream()
                .filter(clientBookings -> clientBookings.containsKey(accommodation))
                .map(clientBookings -> clientBookings.get(accommodation))
                .noneMatch(bookedDates -> !date.isBefore(bookedDates[0]) && !date.isAfter(bookedDates[1]));
    }

    public List<Booking> getBookingHistory() {
        return bookingHistory;
    }
    public void printBookings() {
        System.out.println("Bookings:");
        bookings.forEach((client, accommodationBookings) ->
                accommodationBookings.forEach((accommodation, dates) ->
                        System.out.println(client.firstName() + " " +
                                client.lastName() + " booked " +
                                accommodation.getName() + " from " +
                                dates[0] + " to " +
                                dates[1])
                )
        );
    }
    public Booking getBookingHistoryForAccommodation(Accommodation accommodation) {
        return bookingHistory.stream()
                .filter(booking -> booking.getAccommodation().equals(accommodation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No bookings found for the accommodation: " + accommodation.getName()));
    }
}
