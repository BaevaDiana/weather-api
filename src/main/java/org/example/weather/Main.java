package org.example.weather;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
//        handler.addServlet(new ServletHolder(new WeatherServlet()), "/weather");
//        server.setHandler(handler);
        // Передаем класс сервлета вместо его экземпляра
        handler.addServlet(new ServletHolder(WeatherServlet.class), "/weather");

        server.start();
        System.out.println("Server started at http://localhost:8080/weather?city=Krasnodar");
        server.join();
    }
}
