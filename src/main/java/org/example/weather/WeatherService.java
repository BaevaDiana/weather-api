package org.example.weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class WeatherService {
    private final WeatherClient weatherClient;
    private final ObjectMapper objectMapper;

    public WeatherService() {
        this.weatherClient = new WeatherClient();
        this.objectMapper = new ObjectMapper();
    }

    public String getWeatherInfo(String city) {
        try {
            String response = weatherClient.getWeather(city); //запрос данных по API
            JsonNode jsonNode = objectMapper.readTree(response); //парсинг JSON

            if (jsonNode.has("error")) {
                return "Ошибка: " + jsonNode.get("error").get("message").asText();
            }

            // извлечение данных для прогноза погоды
            String locationName = jsonNode.path("location").path("name").asText("Неизвестно");
            String country = jsonNode.path("location").path("country").asText("Неизвестно");
            String localtime = jsonNode.path("location").path("localtime").asText("Неизвестно");
            double temp = jsonNode.path("current").path("temp_c").asDouble(Double.NaN);
            String condition = jsonNode.path("current").path("condition").path("text").asText("Нет данных");
            double windSpeed = jsonNode.path("current").path("wind_kph").asDouble(Double.NaN);
            String windDirection = jsonNode.path("current").path("wind_dir").asText("Нет данных");
            int humidity = jsonNode.path("current").path("humidity").asInt(-1);
            double feelsLike = jsonNode.path("current").path("feelslike_c").asDouble(Double.NaN);
            double uvIndex = jsonNode.path("current").path("uv").asDouble(Double.NaN);

            return String.format(
                    "Погода в %s, %s (Время: %s):\n" +
                            "Температура: %.1f°C (по ощущениям: %.1f°C)\n" +
                            "Условия: %s\n" +
                            "Ветер: %.1f км/ч, направление: %s\n" +
                            "Влажность: %d%%\n" +
                            "УФ-индекс: %.1f",
                    locationName, country, localtime, temp, feelsLike, condition, windSpeed, windDirection, humidity, uvIndex
            );

        } catch (IOException e) {
            System.err.println("Ошибка сети или API: " + e.getMessage());
            return "Ошибка при получении данных о погоде.";
        } catch (Exception e) {
            System.err.println("Неизвестная ошибка: " + e.getMessage());
            return "Произошла неизвестная ошибка.";
        }
    }

}
