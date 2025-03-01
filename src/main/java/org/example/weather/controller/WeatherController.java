package org.example.weather.controller;

import org.example.weather.service.WeatherService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

// замена класса WeatherServlet из JDBC
// контроллер для получения статистики о погоде
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