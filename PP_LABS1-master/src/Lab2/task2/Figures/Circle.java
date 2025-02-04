package Lab2.task2.Figures;
import Lab2.task2.AreaCalculable;

public class Circle implements AreaCalculable {
    protected final double radius;

    public Circle(double radius) {
        if (radius < 0) {
            System.out.println("Radius cannot be negative. Setting radius to 0.");
            this.radius = 0;
        } else {
            this.radius = radius;
        }
    }


    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}