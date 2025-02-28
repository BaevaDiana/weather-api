package org.example.weather.repository;

import org.example.weather.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// работа с БД с использованием Spring Data JPA
// заменяна JDBC-запросов, Hibernate сам управляет запросами к БД
@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
}