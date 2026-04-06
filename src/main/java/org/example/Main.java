package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    private static String API_KEY;
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    private static void loadApiKey() {
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
            API_KEY = prop.getProperty("api.key"); // Должно совпадать с именем в файле!
        } catch (IOException ex) {
            System.err.println("Ошибка: Не удалось найти файл config.properties или прочитать api.key");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        loadApiKey();

        if (API_KEY == null || API_KEY.isEmpty()) {
            System.out.println("Критическая ошибка: API_KEY не найден в файле!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        String answer;
        while (true) {
            System.out.println("=== Currency Converter ===");
            System.out.print("Enter source currency (for example, GBP): ");
            String fromCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Enter target currency (for example, EUR): ");
            String toCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            double rate = getExchangeRate(fromCurrency, toCurrency);

            if (rate > 0) {
                double result = amount * rate;
                System.out.printf("\n%.2f %s = %.2f %s%n", amount, fromCurrency, result, toCurrency);
                System.out.printf("Exchange rate: 1 %s = %.4f %s%n", fromCurrency, rate, toCurrency);
            } else {
                System.out.println("Failed to get exchange rate. Check the currency code.");
            }
            System.out.println("Do you want to continue? (yes/no)");
            answer = scanner.nextLine();
            if (answer.equals("no")) {
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