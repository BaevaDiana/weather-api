package org.example.weather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherClient {
    private static final String API_KEY = "2517410755584e89a88142724252901";
    private static final String BASE_URL = "http://api.weatherapi.com/v1/current.json?key=";
    private final HttpClient httpClient;

    public WeatherClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getWeather(String city) throws IOException, InterruptedException {
        String url = BASE_URL + API_KEY + "&q=" + city + "&aqi=no";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}