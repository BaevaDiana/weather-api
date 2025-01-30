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

    public String getWeatherInfo(String city) throws IOException, InterruptedException {
        // Получаем ответ от WeatherAPI
        String response = weatherClient.getWeather(city);
        JsonNode jsonNode = objectMapper.readTree(response);

        // Получаем данные о местоположении
        String locationName = jsonNode.get("location").get("name").asText();
        String region = jsonNode.get("location").get("region").asText();
        String country = jsonNode.get("location").get("country").asText();

        // Получаем данные о текущей погоде
        double temp = jsonNode.get("current").get("temp_c").asDouble(); // Температура в градусах Цельсия
        double windSpeed = jsonNode.get("current").get("wind_kph").asDouble(); // Скорость ветра в км/ч
        double uvIndex = jsonNode.get("current").get("uv").asDouble(); // УФ-индекс

        // Формируем строку с информацией о погоде
        return String.format(
                "Погода в %s, %s, %s:\n" +
                        "Температура: %.1f°C\n" +
                        "Ветер: %.1f км/ч\n" +
                        "УФ-индекс: %.1f",
                locationName, region, country, temp, windSpeed, uvIndex
        );
    }

}
