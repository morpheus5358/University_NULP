package Lab3.apartment;

import Lab3.amenity.AType;
import Lab3.booking.Booking;

import java.util.List;


public class AccProcess {
    private final List<Accommodation> accommodations;
    private final List<Booking> bookings;

    public AccProcess(List<Accommodation> accommodations, List<Booking> bookings) {
        this.accommodations = accommodations;
        this.bookings = bookings;
    }

    public List<Accommodation> searchAccommodation(AType amenityType, Type type) {
        return accommodations.stream()
                .filter(accommodation ->
                        (amenityType == null || hasAmenity(accommodation, amenityType)) &&
                                (type == null || hasType(accommodation, type))
                )
                .toList();
    }

    private boolean hasAmenity(Accommodation accommodation, AType amenityType) {
        return accommodation.getAmenities().stream()
                .anyMatch(amenity -> amenity.getAmenityType().equals(amenityType));
    }

    private boolean hasType(Accommodation accommodation, Type type) {
        return accommodation.getType().equals(type);
    }

    public void printStatistics(Accommodation cottage) {
        double totalIncome = calculateTotalIncome(cottage);
        double totalCost = calculateTotalCost(cottage);
        double totalProfit = totalIncome - totalCost;

        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Cost: " + totalCost);
        System.out.println("Total Profit: " + totalProfit);
    }

    private double calculateTotalIncome(Accommodation accommodation) {
        double allCostOnAmenities = accommodation.getAmenities().stream()
                .mapToDouble(amenity -> amenity.getAmenityType().getSpaceRequirement())
                .sum();

        double allCostOnBookings = bookings.stream()
                .filter(booking -> booking.getAccommodation().equals(accommodation))
                .mapToDouble(Booking::getTotalPrice)
                .sum();

        return allCostOnAmenities + allCostOnBookings;
    }

    private double calculateTotalCost(Accommodation accommodation) {
        double dailyAmenitiesCost = calculateDailyAmenitiesCost(accommodation);

        double totalAmenitiesCost = bookings.stream()
                .filter(booking -> booking.getAccommodation().equals(accommodation))
                .mapToDouble(booking -> dailyAmenitiesCost * booking.getDays())
                .sum();

        double totalTaxCost = bookings.stream()
                .filter(booking -> booking.getAccommodation().equals(accommodation))
                .mapToDouble(booking -> booking.getDays() * accommodation.getPrice() * 0.25)
                .sum();

        return totalTaxCost + totalAmenitiesCost;
    }

    private double calculateDailyAmenitiesCost(Accommodation accommodation) {
        return accommodation.getAmenities().stream()
                .mapToDouble(amenity -> switch (amenity.getAmenityType()) {
                    case WIFI -> 5;
                    case KITCHEN -> 10;
                    case BATHROOM -> 8;
                    case JACUZZI -> 15;
                    default -> 0;
                })
                .sum();
    }
}
