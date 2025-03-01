package org.example.weather.client;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

// взаимодействие с сервером, предоставляющим статистику погоды
public class StatisticsClient {
    private final WebClient webClient;

    // WebClient из Spring WebFlux (выполнение HTTP-запроса)
    public StatisticsClient(String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<String> getWeatherStatistics() {
        return webClient.get()
                .uri("/weather/statistics")  // эндпоинт сервера
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class); // JSON-ответ
    }

    public static void main(String[] args) {
        StatisticsClient client = new StatisticsClient("http://localhost:8080");
        client.getWeatherStatistics()
                .subscribe(response -> System.out.println("Полученная статистика: " + response));
    }
}


