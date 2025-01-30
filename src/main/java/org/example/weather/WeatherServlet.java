package org.example.weather;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


//@WebServlet("/weather")
public class WeatherServlet extends HttpServlet  {
    private final WeatherService weatherService;
    private final String city; // Город, введенный пользователем в консоли

    public WeatherServlet(String city) {
        this.weatherService = new WeatherService();
        this.city = city;
    }

    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String city = req.getParameter("city");
//        if (city == null || city.isEmpty()) {
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "City parameter is required");
//            return;
//        }
//        try {
//            String weatherInfo = weatherService.getWeatherInfo(city);
//            resp.setContentType("text/plain;charset=UTF-8");
//            resp.getWriter().write(weatherInfo);
//        } catch (Exception e) {
//            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving weather data");
//        }
//    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String weatherInfo = weatherService.getWeatherInfo(city);
            resp.setContentType("text/plain;charset=UTF-8");
            resp.getWriter().write(weatherInfo);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка получения данных о погоде");
        }
    }
}

