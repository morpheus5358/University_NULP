package Lab2.task2.Figures;
import Lab2.task2.AreaCalculable;
import Lab2.task2.CapacityCalculable;

public class Cube extends Square implements CapacityCalculable {

    public Cube(double side) {
        super(side);
    }

    @Override
    public double calculateArea() {
        return 6 * super.calculateArea();  // Використовуємо площу квадрата
    }

    @Override
    public double calculateCapacity() {
        return side * side * side;  // Об'єм куба
    }
}