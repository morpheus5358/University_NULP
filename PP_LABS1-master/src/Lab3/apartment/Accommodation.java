package Lab3.apartment;
import Lab3.amenity.Amenity;

import java.util.List;

public abstract class Accommodation {
    private final String name;
    private final List<Amenity> amenities;
    private final double price;
    private final Type type;

    protected Accommodation(String name, double price, Type type, List<Amenity> amenities) {
        this.name = name;
        this.amenities = amenities;
        this.price = price;
        this.type = type;
    }

    public abstract void printInfo();

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    @Override
    public String toString() {
        return name;
    }
}
