package Lab1;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть  число: ");
        final int num = scanner.nextInt();

        final String result = toBin(num);
        //final String result2 = toBin2(num);
        System.out.println("Двійковий вигляд числа: " + result);
        //System.out.println("Двійковий вигляд числа: " + result2);
    }

    public static String toBin(int num) {
        String a = "";

        if (num == 0) {
            return "0";
        }

        while (num > 0) {
            final int zal = num % 2;
            a = zal + a;
            num = num / 2;
        }

        return a;
    }
}
    /*public static String toBin2(int num) {
        // Using Integer.toString() to convert to binary
        return Integer.toBinaryString(num);
    }*/


    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Напишіть число: ");
        final int num = scanner.nextInt();
        final String binary = changeToBin(num);
        System.out.println("Двійковий вигляд числа: " + binary);
        scanner.close();
    }


    public static String changeToBin(int num) {
        int[] zalushok = new int[32];
        int index = 0;

        if (num == 0) {
            return "0";
        }

        while (num > 0) {
            zalushok[index] = num % 2;
            num /= 2;
            index++;
        }


        String binary = "";
        for (int i = index - 1; i >= 0; i--) {
            binary += zalushok[i];
        }

        return binary;
    }
*/

