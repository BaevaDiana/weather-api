package org.example.weather;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        // ручная регистрация сервлета
        handler.addServlet(new ServletHolder(new WeatherServlet()), "/weather");
        server.setHandler(handler);

        server.start();
        System.out.println("Сервер запущеен в http://localhost:8080/weather?city=Krasnodar");
        server.join();
    }
}
