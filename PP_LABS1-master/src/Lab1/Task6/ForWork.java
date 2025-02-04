package Lab1.Task6;
import java.util.Scanner;


public class ForWork {
    public static void main(String[] args) {
        final Convertor converter = new Convertor();
        try (final Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введіть валюту у форматі '100 UAH into USD': ");
            final String input = scanner.nextLine();

            final double result = converter.convertCurrency(input);
            System.out.println("Результат переводу: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
