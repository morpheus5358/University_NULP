package Lab3.booking;

import Lab3.Client.Client;
import Lab3.apartment.Accommodation;

import java.time.LocalDate;

public class Booking {
    private static final int MARCH = 3;
    private static final int NOVEMBER = 11;
    private static final double DISCOUNT = 0.8;

    private final Client client;
    private final Accommodation accommodation;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int days;
    private final double pricePerDay;

    public Booking(Client client, Accommodation accommodation, LocalDate startDate, LocalDate endDate, double pricePerDay) {
        this.client = client;
        this.accommodation = accommodation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = (int) (endDate.toEpochDay() - startDate.toEpochDay());
        this.pricePerDay = pricePerDay;
    }

    public double getTotalPrice() {
        double totalCost = 0.0;
        LocalDate currentDay = startDate;

        while (!currentDay.isAfter(endDate.minusDays(1))) {
            double dayCost = pricePerDay;

            if (currentDay.getMonthValue() == MARCH || currentDay.getMonthValue() == NOVEMBER) {
                dayCost *= DISCOUNT;
            }

            totalCost += dayCost;
            currentDay = currentDay.plusDays(1);
        }

        return totalCost;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public int getDays() {
        return days;
    }

    @Override
    public String toString() {
        return "Booking Details:\n" +
                client + "\n" +
                "Accommodation: " + accommodation + "     " +
                "Start Date: " + startDate + "     " +
                "End Date: " + endDate + "     " +
                "Days: " + days + "     " +
                "Price per Day: " + pricePerDay;
    }
}
