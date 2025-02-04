package Lab1.Task4;

import java.util.Scanner;

public class Counter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть рядок: ");
        final String input = scanner.nextLine();
        scanner.close();

        if (input.isEmpty()) {
            System.out.println("Ви ввели пустий рядок");
            return;
        }

        final Task4[] counts = new Task4[input.length()];
        int usc = 0; //uniqe symbol count


        for (int i = 0; i < input.length(); i++) {
            final char currentChar = input.charAt(i);


            boolean found = false;
            for (int j = 0; j < usc; j++) {
                if (counts[j].symbol == currentChar) {
                    counts[j].count++;
                    found = true;
                    break;
                }
            }


            if (!found) {
                counts[usc] = new Task4(currentChar, 1);// constructor
                usc++;
            }
        }


        System.out.println("Кількість кожного символу:");
        for (int i = 0; i < usc; i++) {
            System.out.println(counts[i].symbol + ": " + counts[i].count);
        }
    }
}