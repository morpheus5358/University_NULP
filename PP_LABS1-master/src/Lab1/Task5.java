package Lab1;
import java.util.Scanner;
public class Task5 {

        public static void main(String[] args) {
            final Scanner scanner = new Scanner(System.in);

            System.out.println("Введіть кількість рядків: ");
            final int rows = scanner.nextInt();
            System.out.println("Введіть кількість стовпців: ");
            final int cols = scanner.nextInt();
            scanner.nextLine();

            final String[][] matrix = new String[rows][cols];

            System.out.println("Введіть елементи матриці:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = scanner.nextLine();
                }
            }

            System.out.println("Введіть підрядок для пошуку: ");
            final String substring = scanner.nextLine();

            int count = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    final String currentString = matrix[i][j];
                    count += countOc(currentString, substring);
                }
            }

            System.out.println("Кількість входжень підрядка '" + substring + "': " + count);
        }

        private static int countOc(final String str, final String substr) {
            int count = 0;
            int index = 0;

            while ((index = str.indexOf(substr, index)) != -1) {
                count++;
                index++;
            }

            return count;
        }

    }




