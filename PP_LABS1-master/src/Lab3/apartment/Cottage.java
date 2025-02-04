package Lab3.apartment;
import Lab3.amenity.Amenity;

import java.util.List;


public class Cottage extends Capacity {
    public Cottage(String name, Type type, int maxSpace, double price, List<Amenity> amenities) {
        super(name, maxSpace, price, type, amenities);
    }

    @Override
    public void printInfo() {
        System.out.println("Cottage name: " + super.getName());
        System.out.println("Cottage type: " + super.getType());
        System.out.println("Cottage max space for adults: " + super.getMaxSpace());
        System.out.println("Cottage max space for children: " + super.getChildrenMaxSpace());
        System.out.println("Cottage max space for animals: " + super.getAnimalMaxSpace());

        System.out.println("Cottage amenities: ");
        for (Amenity amenity : super.getAmenities()) {
            System.out.println("Amenity: " + amenity.getAmenityType());
        }
    }
}
