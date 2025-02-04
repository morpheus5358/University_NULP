package Lab1;

import java.util.Scanner;

public class Task2 {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введіть вираз: ");
            final String input = scanner.nextLine();
            scanner.close();

            final String cinp = cexp(input);
            final double result = calculate(cinp);
            System.out.println("Результат: " + result);
        }

        private static String cexp(final String expr) { //clean the expresion
            String tempExpr = expr.replace("=", "");
            tempExpr = tempExpr.replace("?", "");
            return tempExpr.trim();
        }

        private static double calculate(final String expr) {
            final String[] parts = expr.split(" ");
            if (parts.length != 3) {
                System.out.println("Помилка: Невірний формат виразу.");
                return Double.NaN;
            }
// переведення
            final double n1 = parseNumber(parts[0]);
            final double n2 = parseNumber(parts[2]);
            final char oper = parts[1].charAt(0);

            return performOperation(n1, n2, oper);
        }

        private static double parseNumber(final String numStr) {
            try {
                return Double.parseDouble(numStr);
            } catch (NumberFormatException e) {
                System.out.println("Помилка: Невірне число.");
                return Double.NaN;
            }
        }

        private static double performOperation(final double n1, final double n2, final char op) {
            if (op == '+') return n1 + n2;
            if (op == '-') return n1 - n2;
            if (op == '*') return n1 * n2;
            if (op == '/') {
                if (n2 == 0) {
                    System.out.println("Помилка: Ділення на нуль.");
                    return Double.NaN;
                }
                return n1 / n2;
            }
            System.out.println("Помилка: Невідомий оператор.");
            return Double.NaN;
        }
    }






