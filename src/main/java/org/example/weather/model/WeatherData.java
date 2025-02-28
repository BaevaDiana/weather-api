package org.example.weather.model;

import jakarta.persistence.*;

// описание таблицы в БД, замена класса WeatherData из JDBC
@Entity
@Table(name = "weather_data")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_name", nullable = false)
    private String locationName;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "local_time", nullable = false)
    private String local_time;

    @Column(name = "temp_c", nullable = false)
    private double temp;

    @Column(name = "feelslike_c", nullable = false)
    private double feelsLike;

    @Column(name = "condition", nullable = false)
    private String condition;

    @Column(name = "wind_kph", nullable = false)
    private double windSpeed;

    @Column(name = "wind_dir", nullable = false)
    private String windDirection;

    @Column(name = "humidity", nullable = false)
    private int humidity;

    @Column(name = "uv", nullable = false)
    private double uvIndex;

    public WeatherData(){
    }

    public void setId(Long id) { this.id = id; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
    public void setCountry(String country) { this.country = country; }
    public void setLocaltime(String local_time) { this.local_time = local_time; }
    public void setTemp (double temp) { this.temp = temp; }
    public void setFeelsLike(double feelsLike) { this.feelsLike = feelsLike; }
    public void setCondition(String condition) { this.condition = condition; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }
    public void setWindDirection(String windDirection) { this.windDirection = windDirection; }
    public void setHumidity(int humidity) { this.humidity = humidity; }
    public void setUvIndex(double uvIndex) { this.uvIndex = uvIndex; }

    public Long getId() { return id; }
    public String getLocationName() { return locationName; }
    public String getCountry() { return country; }
    public String getLocaltime() { return local_time; }
    public double getTemp() { return temp; }
    public double getFeelsLike() { return feelsLike; }
    public String getCondition() { return condition; }
    public double getWindSpeed() { return windSpeed; }
    public String getWindDirection() { return windDirection; }
    public int getHumidity() { return humidity; }
    public double getUvIndex() { return uvIndex; }
}
