package Lab3.apartment;
import Lab3.amenity.Amenity;

import java.util.ArrayList;
import java.util.List;

public class Hotel extends Accommodation {
    private final List<Room> rooms;

    public Hotel(String name, double price, Type type, List<Amenity> amenities, List<Room> rooms) {
        super(name, price, type, amenities);
        this.rooms = rooms;
    }

    @Override
    public void printInfo() {
        System.out.println("Hotel name: " + super.getName());
        System.out.println("Hotel type: " + super.getType());
        System.out.println("Hotel amenities: ");
        for (Amenity amenity : getAmenities()) {
            System.out.println("Amenity: " + amenity.getAmenityType());
        }

        System.out.println("Rooms: ");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public void printRoomInfo(Room room) {
        System.out.println("Room name: " + room.getName());
        System.out.println("Room max space for adults: " + room.getMaxSpace());
        System.out.println("Room max space for children: " + room.getChildrenMaxSpace());
        System.out.println("Room max space for animals: " + room.getAnimalMaxSpace());

        List<Amenity> uniqueAmenities = new ArrayList<>(room.getAmenities());
        uniqueAmenities.addAll(getAmenities());

        if (uniqueAmenities.isEmpty()) {
            System.out.println("No amenities available for this room and hotel.");
        } else {
            System.out.println("Combined amenities: ");
            for (Amenity amenity : uniqueAmenities) {
                System.out.println("Amenity: " + amenity.getAmenityType());
            }
        }
    }
}
