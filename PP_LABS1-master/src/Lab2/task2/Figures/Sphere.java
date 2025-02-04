package Lab2.task2.Figures;
import Lab2.task2.AreaCalculable;
import Lab2.task2.CapacityCalculable;

public class Sphere extends Circle implements CapacityCalculable {

    public Sphere(double radius) {
        super(radius);
    }

    @Override
    public double calculateArea() {
        return 4 * Math.PI * radius * radius;  // Використовуємо площу кола
    }

    @Override
    public double calculateCapacity() {

        return calculateArea() * radius / 3;  // Об'єм сфери
    }

}