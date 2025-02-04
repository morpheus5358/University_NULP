package Lab2.task3;
public class Good {
    private final double length;
    private final double width;
    private final double height;

    public Good(double length, double width, double height) {
        if (length <= 0 || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Length, width, and height must be positive.");
        }
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getVolume() {
        return length * width * height;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Good with dimensions: " +
                "length=" + length +
                ", width=" + width +
                ", height=" + height;
    }
}
