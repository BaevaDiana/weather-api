package org.example.weather.service;

import org.example.weather.client.WeatherClient;
import org.example.weather.model.WeatherData;
import org.example.weather.repository.WeatherDataRepository;
import org.springframework.stereotype.Service;

// реализация бизнес-логики, реализация класса WeatherService из JDBC
@Service
public class WeatherService {
    private final WeatherClient weatherClient;
    private final WeatherDataRepository weatherDataRepository;

    public WeatherService(WeatherClient weatherClient, WeatherDataRepository weatherDataRepository) {
        this.weatherClient = weatherClient;
        this.weatherDataRepository = weatherDataRepository;
    }

    public WeatherData getWeatherInfo(String city) {
        WeatherData data = weatherClient.fetchWeather(city);
        return weatherDataRepository.save(data);
    }
}
