package Lab2.task1;
import java.util.Scanner;

public class Work {
    public static void main(String[] args) {
        final GraphMethods graph = new GraphMethods();

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Choose the input type:");
            System.out.println("1. Predefined values");
            System.out.println("2. User input");

            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            if (choice == 1) {
                graph.addTop("A", 1);
                graph.addTop("B", 2);
                graph.addTop("C", 3);
                graph.addTop("D", 4);
                graph.addTop("E", 5);

                graph.addNode("A", "B", true);
                graph.addNode("B", "C", true);
                graph.addNode("C", "D", true);
                graph.addNode("D", "E", true);

                // Виведення графу
                graph.displayGraph();

                // Перевірка найкоротшого шляху між вершинами

            } else if (choice == 2) {
                // Введення користувача
                graph.displayGraphFromUserInput();
            } else {
                System.out.println("Invalid option!");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close(); // Закриття сканера
        }
    }

}
