package Lab3.amenity;
public class Amenity {
    private final AType amenityType;
    private final int additionalSpace;
    private final Category category;

    public Amenity(AType amenityType) {
        this.amenityType = amenityType;
        this.additionalSpace = 0;
        this.category = null;
    }

    public Amenity(AType amenityType, int additionalSpace, Category category) {
        this.amenityType = amenityType;
        this.additionalSpace = additionalSpace;
        this.category = category;
    }

    // Getters
    public AType getAmenityType() {
        return amenityType;
    }

    public int getAdditionalSpace() {
        return additionalSpace;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Amenity: " + amenityType +
                ", Additional Space: " + additionalSpace +
                ", Category: " + (category != null ? category : "None");
    }
}
