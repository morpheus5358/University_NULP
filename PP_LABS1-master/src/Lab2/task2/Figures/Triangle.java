package Lab2.task2.Figures;
import Lab2.task2.AreaCalculable;

public class Triangle implements AreaCalculable {
    protected final double base;
    protected final double height;

    public Triangle(double base, double height) {
        if (base < 0 || height < 0) {
            System.out.println("Base and height cannot be negative. Setting both to 0.");
            this.base = 0;
            this.height = 0;
        } else {
            this.base = base;
            this.height = height;
        }
    }
    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}