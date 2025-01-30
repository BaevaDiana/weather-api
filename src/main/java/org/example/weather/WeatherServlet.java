package org.example.weather;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WeatherServlet extends HttpServlet  {
    private final WeatherService weatherService;
    private final String city;

    public WeatherServlet(String city) {
        this.weatherService = new WeatherService();
        this.city = city;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String weatherInfo = weatherService.getWeatherInfo(city); //получение прогноза погоды
            resp.setContentType("text/plain;charset=UTF-8");
            resp.getWriter().write(weatherInfo);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) { // ошибка парсинга JSON
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка обработки данных о погоде");
        } catch (NullPointerException e) { // API вернуло null
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Город не найден, проверьте правильность ввода");
        } catch (IOException e) { // Ошибка сети/API недоступно
            resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Сервис погоды временно недоступен");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Неизвестная ошибка");
        }
    }

}

