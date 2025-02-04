package Lab3.amenity;

public enum AType {
    WIFI(10),
    KITCHEN(25),
    BATHROOM(20),
    JACUZZI(30),
    GYM(60),
    TENNIS_COURT(80),
    POOL(75),
    BED(15),
    KING_SIZE_BED(20),
    PET_BED(5),
    BBQ_GRILL(15),
    FIREPLACE(25),
    PRIVATE_PATIO(40), ;

    private final int spaceRequirement;

    AType(int spaceRequirement) {
        this.spaceRequirement = spaceRequirement;
    }

    public int getSpaceRequirement() {
        return spaceRequirement;
    }
}
