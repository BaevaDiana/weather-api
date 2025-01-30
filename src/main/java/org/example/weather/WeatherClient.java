package org.example.weather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherClient {
    private static final String API_KEY = "2517410755584e89a88142724252901";
    private static final String BASE_URL = "http://api.weatherapi.com/v1/current.json?key=";
    private final HttpClient httpClient; //базовый URL для API

    public WeatherClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getWeather(String city) {
        String url = BASE_URL + API_KEY + "&q=" + city + "&aqi=no"; // полный URL для API

        try {
            HttpRequest request = HttpRequest.newBuilder() //создание HTTP-запроса методом GET
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // отправка запросы и получение ответа
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IOException("Ошибка HTTP: " + response.statusCode() + " - " + response.body());
            }

            return response.body();

        } catch (IOException e) {
            System.err.println("Ошибка сети или API: " + e.getMessage());
            return "{\"error\": \"Ошибка сети или API\"}";
        } catch (InterruptedException e) {
            System.err.println("Запрос был прерван: " + e.getMessage());
            Thread.currentThread().interrupt();
            return "{\"error\": \"Запрос был прерван\"}";
        } catch (Exception e) {
            System.err.println("Неизвестная ошибка: " + e.getMessage());
            return "{\"error\": \"Неизвестная ошибка\"}";
        }
    }
}


