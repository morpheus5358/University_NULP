package Lab2.task2;

import java.util.Scanner;
import Lab2.task2.Figures.Circle;
import Lab2.task2.Figures.Cube;
import Lab2.task2.Figures.Pyramid;
import Lab2.task2.Figures.Sphere;
import Lab2.task2.Figures.Rectangle;
import Lab2.task2.Figures.Square;
import Lab2.task2.Figures.Triangle;


public class Work {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose input method:");
        System.out.println("1. Predefined values");
        System.out.println("2. User input");

        int choice = scanner.nextInt();

        if (choice == 1) {
            // Using predefined values
            Triangle triangle = new Triangle(10, 5);
            Cube cube = new Cube(4);
            Circle circle = new Circle(3);

            System.out.println("Triangle area: " + triangle.calculateArea());
            System.out.println("Cube area: " + cube.calculateArea());
            System.out.println("Cube capacity: " + cube.calculateCapacity());
            System.out.println("Circle area: " + circle.calculateArea());
        } else if (choice == 2) {
            System.out.println("Choose a figure:");
            System.out.println("1. Triangle");
            System.out.println("2. Cube");
            System.out.println("3. Circle");
            System.out.println("4. Square");
            System.out.println("5. Rectangle");
            System.out.println("6. Pyramid");
            System.out.println("7. Sphere");

            int figureChoice = scanner.nextInt();

            switch (figureChoice) {
                case 1: // Triangle
                    System.out.print("Enter base of the triangle: ");
                    double base = scanner.nextDouble();
                    System.out.print("Enter height of the triangle: ");
                    double height = scanner.nextDouble();
                    Triangle triangle = new Triangle(base, height);
                    System.out.println("Triangle area: " + triangle.calculateArea());
                    break;

                case 2: // Cube
                    System.out.print("Enter side of the cube: ");
                    double sideCube = scanner.nextDouble();
                    Cube cube = new Cube(sideCube);
                    System.out.println("Cube area: " + cube.calculateArea());
                    System.out.println("Cube capacity: " + cube.calculateCapacity());
                    break;

                case 3: // Circle
                    System.out.print("Enter radius of the circle: ");
                    double radius = scanner.nextDouble();
                    Circle circle = new Circle(radius);
                    System.out.println("Circle area: " + circle.calculateArea());
                    break;

                case 4: // Square
                    System.out.print("Enter side of the square: ");
                    double sideSquare = scanner.nextDouble();
                    Square square = new Square(sideSquare);
                    System.out.println("Square area: " + square.calculateArea());
                    break;

                case 5: // Rectangle
                    System.out.print("Enter length of the rectangle: ");
                    double length = scanner.nextDouble();
                    System.out.print("Enter width of the rectangle: ");
                    double width = scanner.nextDouble();
                    Rectangle rectangle = new Rectangle(length, width);
                    System.out.println("Rectangle area: " + rectangle.calculateArea());
                    break;

                case 6: // Pyramid
                    System.out.print("Enter base of the pyramid (triangle base): ");
                    double basePyramid = scanner.nextDouble();
                    System.out.print("Enter height of the triangle base: ");
                    double heightPyramid = scanner.nextDouble();
                    System.out.print("Enter pyramid height: ");
                    double pyramidHeight = scanner.nextDouble();
                    Pyramid pyramid = new Pyramid(basePyramid, heightPyramid, pyramidHeight);
                    System.out.println("Pyramid area: " + pyramid.calculateArea());
                    System.out.println("Pyramid capacity: " + pyramid.calculateCapacity());
                    break;

                case 7: // Sphere
                    System.out.print("Enter radius of the sphere: ");
                    double radiusSphere = scanner.nextDouble();
                    Sphere sphere = new Sphere(radiusSphere);
                    System.out.println("Sphere area: " + sphere.calculateArea());
                    System.out.println("Sphere capacity: " + sphere.calculateCapacity());
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid figure.");
                    break;
            }
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
