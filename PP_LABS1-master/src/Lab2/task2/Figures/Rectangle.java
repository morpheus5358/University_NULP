package Lab2.task2.Figures;
import Lab2.task2.AreaCalculable;

public class Rectangle implements AreaCalculable {
    protected final double length;
    protected final double width;

    public Rectangle(double length, double width) {
        if (length < 0 || width < 0) {
            System.out.println("Length and width cannot be negative. Setting both to 0.");
            this.length = 0;
            this.width = 0;
        } else {
            this.length = length;
            this.width = width;
        }
    }

    @Override
    public double calculateArea() {
        return length * width;
    }
}