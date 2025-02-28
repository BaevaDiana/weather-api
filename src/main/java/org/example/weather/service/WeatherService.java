package org.example.weather.service;

import org.example.weather.model.WeatherData;
import org.example.weather.repository.WeatherDataRepository;
import org.springframework.stereotype.Service;
import java.util.List;

// реализация бизнес-логики, реализация класса WeatherService из JDBC
@Service
public class WeatherService {
    private final WeatherDataRepository repository;

    public WeatherService(WeatherDataRepository repository) {
        this.repository = repository;
    }

    public String getWeatherStatistics() {
        List<WeatherData> allData = repository.findAll();

        if (allData.isEmpty()) {
            return "Нет данных о погоде.";
        }

        double avgTemp = allData.stream().mapToDouble(WeatherData::getTemp).average().orElse(0);
        double avgHumidity = allData.stream().mapToInt(WeatherData::getHumidity).average().orElse(0);

        return String.format("Средняя температура: %.1f°C, Средняя влажность: %.1f%%", avgTemp, avgHumidity);
    }
}
