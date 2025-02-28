package org.example.weather.controller;

import org.example.weather.service.WeatherService;
import org.example.weather.model.WeatherData;
import org.springframework.web.bind.annotation.*;

// данный контроллер заменяет класс WeatherServlet из JDBC
@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    // получение данных о погоде
    @GetMapping
    public WeatherData getWeather(@RequestParam String city){
        return weatherService.getWeatherInfo(city);
    }
}
