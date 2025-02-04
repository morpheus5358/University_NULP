package Lab2.task2;

public interface AreaCalculable {
    double calculateArea();
    default double A () {
        return 0.0;

    };
}
