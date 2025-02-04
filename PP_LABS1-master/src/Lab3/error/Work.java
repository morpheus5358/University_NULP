package Lab3.error;

import Lab3.Client.Client;
import Lab3.amenity.AType;
import Lab3.amenity.Amenity;
import Lab3.amenity.Category;
import Lab3.apartment.*;
import Lab3.booking.Booking;
import Lab3.booking.BProcess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Work {
    private static final String OPERATOR = "\n\n\n";

    public static void main(String[] args) {
        Work work = new Work();
        work.run();
    }

    public void run() {
        // Initialize resources
        List<Amenity> amenitiesCottage1 = new ArrayList<>(List.of(
                new Amenity(AType.WIFI),
                new Amenity(AType.KITCHEN),
                new Amenity(AType.BATHROOM),
                new Amenity(AType.GYM),
                new Amenity(AType.KING_SIZE_BED, 2, Category.ADULT)
        ));
        Accommodation cottage1 = new Cottage("Cottage 1", Type.STANDARD, 2, 6000, amenitiesCottage1);

        List<Amenity> amenitiesCottage2 = new ArrayList<>(List.of(
                new Amenity(AType.WIFI),
                new Amenity(AType.KITCHEN),
                new Amenity(AType.BATHROOM),
                new Amenity(AType.GYM),
                new Amenity(AType.BED, 1, Category.ADULT)
        ));
        Accommodation cottage2 = new Cottage("Cottage 2", Type.STANDARD, 6, 2500, amenitiesCottage2);

        List<Amenity> amenitiesCottage3 = new ArrayList<>(List.of(
                new Amenity(AType.WIFI),
                new Amenity(AType.KITCHEN),
                new Amenity(AType.BATHROOM),
                new Amenity(AType.JACUZZI),
                new Amenity(AType.GYM),
                new Amenity(AType.TENNIS_COURT),
                new Amenity(AType.POOL),
                new Amenity(AType.PET_BED, 1, Category.KID)
        ));
        Accommodation cottage3 = new Cottage("Cottage 3", Type.LUXURY, 10, 5000, amenitiesCottage3);

        List<Amenity> amenitiesHotel = new ArrayList<>(List.of(
                new Amenity(AType.WIFI),
                new Amenity(AType.KITCHEN),
                new Amenity(AType.BATHROOM)
        ));
        Room room1 = new Room("Room 1", Type.APARTMENT, 3, 800, List.of(
                new Amenity(AType.WIFI),
                new Amenity(AType.KITCHEN, 1, Category.ADULT)
        ));
        Room room2 = new Room("Room 2", Type.APARTMENT, 4, 1000, List.of(
                new Amenity(AType.WIFI),
                new Amenity(AType.JACUZZI),
                new Amenity(AType.KING_SIZE_BED, 2, Category.ADULT)
        ));
        Room room3 = new Room("Room 3", Type.APARTMENT, 3, 1500, new ArrayList<>());
        Hotel hotel1 = new Hotel("Hotel 1", 5000, Type.LUXURY, amenitiesHotel, List.of(room1, room2, room3));

        Client client1 = new Client("Oleg", "Petrenko", "+380987654321", "oleg@gmail.com");

        LocalDate bookingDate1 = LocalDate.parse("2024-05-10");
        LocalDate bookingDate2 = LocalDate.parse("2024-05-17");

        System.out.println(cottage3.getName());
        cottage3.printInfo();
        System.out.println(OPERATOR);
        hotel1.printInfo();
        System.out.println(OPERATOR);
        hotel1.printRoomInfo(room1); // subtask 4
        System.out.println(OPERATOR);

        BProcess bookingService = new BProcess();

        checkIsDateAvailable(bookingService, cottage1, bookingDate1); // subtask 6
        try {
            bookingService.bookAccommodation(client1, cottage1, bookingDate1, bookingDate2); // subtask 7
            bookingService.bookAccommodation(client1, cottage1, bookingDate1, bookingDate1);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
        checkIsDateAvailable(bookingService, cottage1, bookingDate1);

        bookingService.printBookings(); // Method to display all bookings

        try {
            System.out.println(bookingService.getBookingHistoryForAccommodation(cottage1)); // subtask additional
            System.out.println(bookingService.getBookingHistoryForAccommodation(cottage2));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        System.out.println(OPERATOR);

        AccProcess accommodationService = new AccProcess(getAccommodations(cottage1, cottage2, cottage3, hotel1), bookingService.getBookingHistory());

        accommodationService.printStatistics(cottage1); // Calculation for subtask 8

        List<Accommodation> foundAccommodations = accommodationService.searchAccommodation(AType.WIFI, Type.LUXURY); // subtask 10
        foundAccommodations.forEach(accommodation -> System.out.println(accommodation.getName()));
    }

    private List<Accommodation> getAccommodations(Accommodation... accommodations) {
        return List.of(accommodations);
    }

    private void checkIsDateAvailable(BProcess bookingService, Accommodation accommodation, LocalDate date) {
        if (bookingService.isDateAvailable(accommodation, date)) {
            System.out.println("Accommodation " + accommodation.getName() + " is available on " + date);
        } else {
            System.err.println("Accommodation " + accommodation.getName() + " is NOT available on " + date);
        }
    }
}
