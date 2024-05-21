package dev.practice.usespringwebflux.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class TargetClient {

    private final WebClient webClient;

    public Mono<ResponseEntity<String>> request(String targetUrl, HttpHeaders headers, String body) {
        return request(targetUrl, headers, body, null);
    }

    public Mono<ResponseEntity<String>> request(String targetUrl, HttpHeaders headers, String body, String hostHeader) {

        return webClient
                .post()
                .uri(URI.create(targetUrl + "/request"))
                .headers(
                        httpHeaders -> {
                            httpHeaders.putAll(headers);
                            if(hostHeader != null) {
                                httpHeaders.set(HttpHeaders.HOST, hostHeader);
                            }
                        }
                )
                .bodyValue(body)
                .exchangeToMono(
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .map(
                                        responseBody -> ResponseEntity.status(clientResponse.statusCode())
                                                .headers(clientResponse.headers().asHttpHeaders())
                                                .body(responseBody)
                                )
                )
                .onErrorResume(
                        WebClientRequestException.class,
                        ex -> Mono.just(ResponseEntity.status(504).body("Target server something went wrong.. " + ex.getMessage()))
                );
    }
}
