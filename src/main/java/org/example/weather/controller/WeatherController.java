package org.example.weather.controller;

import org.example.weather.service.WeatherService;
import org.example.weather.model.WeatherData;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

// данный контроллер заменяет класс WeatherServlet из JDBC
@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    // получение статистики данных о погоде
    @GetMapping("/statistics")
    public ResponseEntity<String> getWeatherStatistics() {
        String statistics = weatherService.getWeatherStatistics();
        return ResponseEntity.ok(statistics);
    }
}