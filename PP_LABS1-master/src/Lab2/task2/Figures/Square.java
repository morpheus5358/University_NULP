package Lab2.task2.Figures;

import Lab2.task2.AreaCalculable;

public class Square implements AreaCalculable {
    protected final double side;

    public Square(double side) {
        if (side < 0) {
            System.out.println("Side cannot be negative. Setting side to 0.");
            this.side = 0;
        } else {
            this.side = side;
        }
    }

    @Override
    public double calculateArea() {
        return side * side;
    }
}