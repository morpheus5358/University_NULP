package Lab3.apartment;
import Lab3.amenity.Amenity;
import Lab3.amenity.Category;

import java.util.List;

public abstract class Capacity extends Accommodation {
    protected int maxSpace;
    protected int childrenMaxSpace;
    protected int animalMaxSpace;

    protected Capacity(String name, int maxSpace, double price, Type type, List<Amenity> amenities) {
        super(name, price, type, amenities);
        validateMaxSpace(maxSpace);
        this.maxSpace = maxSpace;
        this.childrenMaxSpace = 0;
        this.animalMaxSpace = 0;

        for (Amenity amenity : amenities) {
            if (amenity.getCategory() != null) {
                switch (amenity.getCategory()) {
                    case ADULT -> this.maxSpace += amenity.getAdditionalSpace();
                    case KID -> this.childrenMaxSpace += amenity.getAdditionalSpace();
                    case PET -> this.animalMaxSpace += amenity.getAdditionalSpace(); //зручності можуть збільшувати простір для певної категорії
                }
            }
        }
    }

    private void validateMaxSpace(int maxSpace) {
        if (maxSpace < 0) {
            throw new IllegalArgumentException("Max space cannot be negative.");
        }
    }

    public int getMaxSpace() {
        return maxSpace;
    }

    public int getChildrenMaxSpace() {
        return childrenMaxSpace;
    }

    public int getAnimalMaxSpace() {
        return animalMaxSpace;
    }
}
