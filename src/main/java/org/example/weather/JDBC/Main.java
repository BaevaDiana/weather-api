package org.example.weather.JDBC;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String city;

        // валидация корректного ввода города
        while (true) {
            System.out.print("Введите название города для прогноза погоды: ");
            city = scanner.nextLine().trim();

            if (!city.isEmpty() && city.matches("[a-zA-Zа-яА-ЯёЁ\\s-]+")) {
                break;
            }
            System.out.println("Ошибка: название города должно содержать только буквы. Попробуйте снова.");
        }
        scanner.close();

        Server server = new Server(8080);
        // обработчик сервлетов
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        WeatherServlet weatherServlet = new WeatherServlet(city);
        // регистрация сервлета по пути
        handler.addServlet(new ServletHolder(weatherServlet), "/weather");

        // установка обработчика сервлетов в качестве основного для сервера
        server.setHandler(handler);

        server.start();
        System.out.println("Сервер запущен в http://localhost:8080/weather");
        // удержка сервера в активном состоянии
        server.join();
    }
}
