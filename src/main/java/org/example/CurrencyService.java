package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CurrencyService {

    // Спринг сам прочитает значение из application.properties или переменной окружения
    @Value("${api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    // Весь метод init() с ручным чтением файлов больше НЕ НУЖЕН! Спринг всё сделает сам.

    public double getExchangeRate(String from, String to) throws Exception {
        String url = BASE_URL + apiKey + "/pair/" + from + "/" + to;

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