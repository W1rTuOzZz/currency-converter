package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    // Сюда вставишь свой ключ с exchangerate-api.com
    private static final String API_KEY = "de53f558b2a5f0de21c2191f";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String answer;
        while (true) {
            System.out.println("=== Конвертер валют ===");
            System.out.print("Введите исходную валюту (например, USD): ");
            String fromCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Введите целевую валюту (например, KGS): ");
            String toCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Введите сумму: ");
            double amount = Double.parseDouble(scanner.nextLine());

            double rate = getExchangeRate(fromCurrency, toCurrency);

            if (rate > 0) {
                double result = amount * rate;
                System.out.printf("\n%.2f %s = %.2f %s%n", amount, fromCurrency, result, toCurrency);
                System.out.printf("Курс: 1 %s = %.4f %s%n", fromCurrency, rate, toCurrency);
            } else {
                System.out.println("Не удалось получить курс. Проверьте код валюты.");
            }
            System.out.println("Хотите продолжить? (да/нет)");
            answer = scanner.nextLine();
            if (answer.equals("нет")) {
                break;
            }
        }
    }

    private static double getExchangeRate(String from, String to) throws Exception {
        String url = BASE_URL + API_KEY + "/pair/" + from + "/" + to;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        JsonObject json = JsonParser.parseString(body).getAsJsonObject();
        String result = json.get("result").getAsString();

        if (result.equals("success")) {
            return json.get("conversion_rate").getAsDouble();
        } else {
            return -1;
        }
    }
}