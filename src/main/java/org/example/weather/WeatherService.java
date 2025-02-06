package org.example.weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class WeatherService {
    private final WeatherClient weatherClient;
    private final ObjectMapper objectMapper;
    private final WeatherDatabase weatherDataBase;

    public WeatherService() {
        this.weatherClient = new WeatherClient();
        this.objectMapper = new ObjectMapper();
        this.weatherDataBase = new WeatherDatabase();
    }

    public String getWeatherInfo(String city) throws IOException, InterruptedException {
        String response = weatherClient.getWeather(city);
        JsonNode jsonNode = objectMapper.readTree(response);

        String locationName = jsonNode.get("location").get("name").asText();
        String country = jsonNode.get("location").get("country").asText();
        String local_time = jsonNode.get("location").get("localtime").asText();
        double temp = jsonNode.get("current").get("temp_c").asDouble();
        String condition = jsonNode.get("current").get("condition").get("text").asText();
        double windSpeed = jsonNode.get("current").get("wind_kph").asDouble();
        String windDirection = jsonNode.get("current").get("wind_dir").asText();
        int humidity = jsonNode.get("current").get("humidity").asInt();
        double feelsLike = jsonNode.get("current").get("feelslike_c").asDouble();
        double uvIndex = jsonNode.get("current").get("uv").asDouble();

        WeatherData weatherData = new WeatherData(locationName, country, local_time, temp, feelsLike, condition,
                windSpeed, windDirection, humidity, uvIndex);
        // сохранение данных в БД
        weatherDataBase.saveWeatherData(weatherData);

        return String.format(
                "Погода в %s, %s (Время: %s):\n" +
                        "Температура: %.1f°C (по ощущениям: %.1f°C)\n" +
                        "Условия: %s\n" +
                        "Ветер: %.1f км/ч, направление: %s\n" +
                        "Влажность: %d%%\n" +
                        "УФ-индекс: %.1f",
                locationName, country, local_time, temp, feelsLike, condition, windSpeed, windDirection, humidity, uvIndex
        );
    }
}
