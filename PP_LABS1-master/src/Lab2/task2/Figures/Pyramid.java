package Lab2.task2.Figures;
import Lab2.task2.AreaCalculable;
import Lab2.task2.CapacityCalculable;

public class Pyramid extends Triangle implements CapacityCalculable {
    private final double height;

    public Pyramid(double base, double height, double pyramidHeight) {
        super(base, height);
        if (pyramidHeight < 0) {
            System.out.println("Pyramid height cannot be negative. Setting pyramid height to 0.");
            this.height = 0;
        } else {
            this.height = pyramidHeight;
        }
    }

    @Override
    public double calculateArea() {
        return super.calculateArea();  // Використовуємо площу трикутника
    }

    @Override
    public double calculateCapacity() {
        return (1.0 / 3.0) * super.calculateArea() * height;  // Об'єм піраміди
    }
}