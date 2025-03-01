package org.example.weather.service;

import org.example.weather.model.WeatherData;
import org.example.weather.repository.WeatherDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherDataService {
    private final WeatherDataRepository weatherDataRepository;

    public WeatherDataService(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    // сохранение данных о погоде
    public WeatherData saveWeatherData(WeatherData data) {
        return weatherDataRepository.save(data);
    }

    // получение всех данных о погоде
    public List<WeatherData> getAllWeatherData() {
        return weatherDataRepository.findAll();
    }

    // получение данных по ID
    public Optional<WeatherData> getWeatherDataById(Long id) {
        return weatherDataRepository.findById(id);
    }

    // удаление данных по ID
    public void deleteWeatherData(Long id) {
        weatherDataRepository.deleteById(id);
    }

}
