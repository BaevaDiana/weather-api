package org.example.weather;

import org.example.weather.JDBC.WeatherData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WeatherDatabase {
    private final String url = "jdbc:postgresql://localhost:5433/weather_data_basee"; // PostgreSQL JDBC Driver(Type 4)
    private final String username = "admin";
    private final String password = "secret";

    public void saveWeatherData(WeatherData data) {
        String sql = "INSERT INTO weather_data (location_name, country, local_time, temp_c, feelslike_c, condition, wind_kph, wind_dir, humidity, uv) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, data.getLocationName());
            stmt.setString(2, data.getCountry());
            stmt.setString(3, data.getLocaltime());
            stmt.setDouble(4, data.getTemp());
            stmt.setDouble(5, data.getFeelsLike());
            stmt.setString(6, data.getCondition());
            stmt.setDouble(7, data.getWindSpeed());
            stmt.setString(8, data.getWindDirection());
            stmt.setInt(9, data.getHumidity());
            stmt.setDouble(10, data.getUvIndex());

            stmt.executeUpdate(); // выполнение операции INSERT
            System.out.println("Данные успешно сохранены в БД.");
        } catch (SQLException e) {
            System.err.println("Ошибка при сохранении данных о погоде: " + e.getMessage());
        }
    }
}