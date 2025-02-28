package org.example.weather.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.weather.model.WeatherData;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// замена класса WeatherClient из JDBC
// получение данных о текущей погоде из API
@Component
public class WeatherClient {
    private static final String API_KEY = "2517410755584e89a88142724252901";
    private static final String BASE_URL = "http://api.weatherapi.com/v1/current.json?key=";
    private final HttpClient httpClient; // базовый URL для API
    private final ObjectMapper objectMapper;

    public WeatherClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public WeatherData fetchWeather(String city) {
        String url = BASE_URL + API_KEY + "&q=" + city + "&aqi=no";
        HttpRequest request = HttpRequest.newBuilder() // создание HTTP-запроса методом GET
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return parseWeatherData(response.body());
        } catch (IOException | InterruptedException e){
            throw new RuntimeException("Ошибка получения данных о погоде", e);
        }
    }

    private WeatherData parseWeatherData(String json) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(json);

        WeatherData data = new WeatherData();
        data.setLocationName(jsonNode.get("location").get("name").asText());
        data.setCountry(jsonNode.get("location").get("country").asText());
        data.setLocaltime(jsonNode.get("location").get("localtime").asText());
        data.setTemp(jsonNode.get("current").get("temp_c").asDouble());
        data.setFeelsLike(jsonNode.get("current").get("feelslike_c").asDouble());
        data.setCondition(jsonNode.get("current").get("condition").get("text").asText());
        data.setWindSpeed(jsonNode.get("current").get("wind_kph").asDouble());
        data.setWindDirection(jsonNode.get("current").get("wind_dir").asText());
        data.setHumidity(jsonNode.get("current").get("humidity").asInt());
        data.setUvIndex(jsonNode.get("current").get("uv").asDouble());

        return data;
    }
}
