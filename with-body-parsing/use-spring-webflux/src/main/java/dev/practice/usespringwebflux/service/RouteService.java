package dev.practice.usespringwebflux.service;

import dev.practice.usespringwebflux.client.TargetClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteInfoService routeInfoService;
    private final TargetClient targetClient;

    public Mono<ResponseEntity<String>> route(String issuer, HttpHeaders headers, String body) {

        // 0. issuer 가 존재하는 url 인지 확인
        if(routeInfoService.isValidUrl(issuer) == Boolean.FALSE)
            throw new IllegalArgumentException("Invalid URL"); // todo, 4xx

        // 1. proxy 의 region 에 존재하는 origin server 의 url 과 issuer 비교
        if(routeInfoService.compareUrlByRegion(issuer) == Boolean.TRUE) {
            // 2-1. 동일하면 요청 url 을 internal 로 하여 그대로 전송

            String targetUrl = routeInfoService.getCurrentRegionInternalUrl();

            return targetClient.request(targetUrl, headers, body);

        }else {
            // 2-2. 다르면 요청 url 을 issuer 로 변경하여 전송 + Host 헤더를 issuer 로 변경

            return targetClient.request(issuer, headers, body, issuer);
        }


    }
}
