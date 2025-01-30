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
        String response = weatherClient.getWeather(city);
        JsonNode jsonNode = objectMapper.readTree(response);
        double temp = jsonNode.get("current").get("temp_c").asDouble();
        String condition = jsonNode.get("current").get("condition").get("text").asText();
        return "Погода в " + city + ": " + temp + "°C, " + condition;
    }

}
