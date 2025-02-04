package Lab3.apartment;
import Lab3.amenity.Amenity;

import java.util.List;

public class Room extends Capacity {
    public Room(String name, Type type, int maxSpace, double price, List<Amenity> amenities) {
        super(name, maxSpace, price, type, amenities);
    }

    @Override
    public void printInfo() {
        System.out.println("Room name: " + super.getName());
        System.out.println("Room type: " + super.getType());
        System.out.println("Room max space for adults: " + super.getMaxSpace());
        System.out.println("Room max space for children: " + super.getChildrenMaxSpace());
        System.out.println("Room max space for animals: " + super.getAnimalMaxSpace());

        System.out.println("Room amenities: ");
        for (Amenity amenity : super.getAmenities()) {
            System.out.println("Amenity: " + amenity.getAmenityType());
        }
        System.out.println("");
    }

    @Override
    public String toString() {
        return getName();
    }
}
