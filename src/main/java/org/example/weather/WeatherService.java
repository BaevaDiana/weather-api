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

        // Извлекаем данные о местоположении
        String locationName = jsonNode.get("location").get("name").asText();
        String country = jsonNode.get("location").get("country").asText();
        String localtime = jsonNode.get("location").get("localtime").asText();

        // Извлекаем данные о текущей погоде
        double temp = jsonNode.get("current").get("temp_c").asDouble(); // Температура в градусах Цельсия
        String condition = jsonNode.get("current").get("condition").get("text").asText(); // Описание погоды
        double windSpeed = jsonNode.get("current").get("wind_kph").asDouble(); // Скорость ветра в км/ч
        String windDirection = jsonNode.get("current").get("wind_dir").asText(); // Направление ветра
        int humidity = jsonNode.get("current").get("humidity").asInt(); // Влажность
        double feelsLike = jsonNode.get("current").get("feelslike_c").asDouble(); // Температура по ощущениям
        double uvIndex = jsonNode.get("current").get("uv").asDouble(); // УФ-индекс

        // Формируем строку с информацией о погоде
        return String.format(
                "Погода в %s, %s (Время: %s):\n" +
                        "Температура: %.1f°C (по ощущениям: %.1f°C)\n" +
                        "Условия: %s\n" +
                        "Ветер: %.1f км/ч, направление: %s\n" +
                        "Влажность: %d%%\n" +
                        "УФ-индекс: %.1f",
                locationName, country, localtime, temp, feelsLike, condition, windSpeed, windDirection, humidity, uvIndex
        );
    }

}
