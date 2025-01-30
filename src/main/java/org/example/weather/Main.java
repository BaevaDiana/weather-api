package org.example.weather;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название города для прогноза погоды: ");
        String city = scanner.nextLine().trim();
        scanner.close();

        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        // ручная регистрация сервлета
//        handler.addServlet(new ServletHolder(new WeatherServlet()), "/weather");
//        server.setHandler(handler);
        WeatherServlet weatherServlet = new WeatherServlet(city);
        handler.addServlet(new ServletHolder(weatherServlet), "/weather");

        server.setHandler(handler);
        server.start();

        System.out.println("Сервер запущен в http://localhost:8080/weather");
        server.join();
    }
}
