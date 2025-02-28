package org.example.weather.controller;

import org.example.weather.model.WeatherData;
import org.example.weather.service.WeatherDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/weather")
public class WeatherDataController {
    private final WeatherDataService weatherDataService;

    public WeatherDataController(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }

    // получить все данные о погоде
    @GetMapping
    public List<WeatherData> getAllWeatherData() {
        return weatherDataService.getAllWeatherData();
    }

    // получить данные о погоде по ID
    @GetMapping("/{id}")
    public ResponseEntity<WeatherData> getWeatherDataById(@PathVariable Long id) {
        Optional<WeatherData> weatherData = weatherDataService.getWeatherDataById(id);
        return weatherData.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // сохранить новые данные о погоде
    @PostMapping
    public ResponseEntity<WeatherData> saveWeatherData(@RequestBody WeatherData weatherData) {
        WeatherData savedData = weatherDataService.saveWeatherData(weatherData);
        return ResponseEntity.ok(savedData);
    }

    // удалить данные по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeatherData(@PathVariable Long id) {
        weatherDataService.deleteWeatherData(id);
        return ResponseEntity.noContent().build();
    }
}
