package Lab2.task3;
import java.util.Scanner;

public class Work {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Choose input method:");
            System.out.println("1. Predefined values");
            System.out.println("2. User input");

            int choice = scanner.nextInt();

            if (choice == 1) {
                loadTestData();
            } else if (choice == 2) {
                loadUserData(scanner);
            } else {
                System.out.println("Invalid option.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void loadUserData(Scanner scanner) {
        Warehouse warehouse = new Warehouse();

        System.out.println("Enter the number of cells:");
        int cellCount = scanner.nextInt();
        if (cellCount <= 0) {
            System.out.println("Number of cells must be positive.");
            return;
        }

        for (int i = 0; i < cellCount; i++) {
            System.out.println("Enter dimensions for cell " + (i + 1) + ":");
            double length = scanner.nextDouble();
            double width = scanner.nextDouble();
            double height = scanner.nextDouble();

            Cell cell = new Cell(length, width, height);
            warehouse.addCell(cell);

            System.out.println("Enter the number of goods for cell " + (i + 1) + ":");
            int goodCount = scanner.nextInt();
            if (goodCount <= 0) {
                System.out.println("Number of goods must be positive.");
                return;
            }

            for (int j = 0; j < goodCount; j++) {
                System.out.println("Enter dimensions for good " + (j + 1) + ":");
                double goodLength = scanner.nextDouble();
                double goodWidth = scanner.nextDouble();
                double goodHeight = scanner.nextDouble();

                Good good = new Good(goodLength, goodWidth, goodHeight);
                warehouse.addGoodToCell(cell, good);
                System.out.println("Good successfully added.");
            }
        }

        warehouse.printAllGoods();
    }

    private static void loadTestData() {
        Warehouse warehouse = new Warehouse();

        // Додаємо тестові дані
        Cell cell1 = new Cell(5, 5, 5);
        warehouse.addCell(cell1);

        Good good1 = new Good(1, 1, 1);
        Good good2 = new Good(2, 2, 2);
        warehouse.addGoodToCell(cell1, good1);
        warehouse.addGoodToCell(cell1, good2);

        warehouse.printAllGoods();
    }
}
