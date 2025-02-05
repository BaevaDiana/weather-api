package org.example.weather;

public class WeatherData {
    private String locationName;
    private String country;
    private String local_time;
    private double temp;
    private double feelsLike;
    private String condition;
    private double windSpeed;
    private String windDirection;
    private int humidity;
    private double uvIndex;

    public WeatherData(String locationName, String country, String local_time, double temp, double feelsLike,
                       String condition, double windSpeed, String windDirection, int humidity, double uvIndex) {
        this.locationName = locationName;
        this.country = country;
        this.local_time = local_time;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.condition = condition;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.humidity = humidity;
        this.uvIndex = uvIndex;
    }

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
