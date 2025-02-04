package Lab1.Task6;
import java.util.Scanner;

public class Convertor {
    private static final double UAH_TO_USD = 0.02418;
    private static final double UAH_TO_EUR = 0.02185;
    private static final double UAH_TO_CAD = 0.03306;

    private static final double USD_TO_UAH = 41.34;
    private static final double USD_TO_CAD = 1.367;
    private static final double USD_TO_EUR = 0.903;

    private static final double EUR_TO_USD = 1.107;
    private static final double EUR_TO_CAD = 1.513;
    private static final double EUR_TO_UAH = 45.77;

    private static final double CAD_TO_USD = 0.731;
    private static final double CAD_TO_EUR = 0.661;
    private static final double CAD_TO_UAH = 30.24;

    public double convertCurrency(final String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Введіть щось у форматі '100 UAH into USD'");
        }

        final String[] parts = input.split(" ");
        if (parts.length != 4 ) {
            throw new IllegalArgumentException("Невірний формат. Потрібно '100 UAH into USD'");
        }

        final double amount;

        try {
            amount = Double.parseDouble(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Перша частина має бути числом");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Сума не може бути від'ємною");
        }

        final String fromCurrency = parts[1].toUpperCase();
        final String toCurrency = parts[3].toUpperCase();

        return calculateConversion(amount, fromCurrency, toCurrency);
    }

    private double calculateConversion(final double amount, final String fromCurrency, final String toCurrency) {
        String conversionKey = fromCurrency + "to" + toCurrency;

        return switch (conversionKey) {
            case "UAHtoUSD" -> amount * UAH_TO_USD;
            case "USDtoUAH" -> amount * USD_TO_UAH;
            case "UAHtoEUR" -> amount * UAH_TO_EUR;
            case "EURtoUAH" -> amount * EUR_TO_UAH;
            case "UAHtoCAD" -> amount * UAH_TO_CAD;
            case "CADtoUAH" -> amount * CAD_TO_UAH;
            case "USDtoEUR" -> amount * USD_TO_EUR;
            case "EURtoUSD" -> amount * EUR_TO_USD;
            case "USDtoCAD" -> amount * USD_TO_CAD;
            case "CADtoUSD" -> amount * CAD_TO_USD;
            case "EURtoCAD" -> amount * EUR_TO_CAD;
            case "CADtoEUR" -> amount * CAD_TO_EUR;
            default -> throw new IllegalArgumentException("Невідома  валюта");
        };
    }

}
