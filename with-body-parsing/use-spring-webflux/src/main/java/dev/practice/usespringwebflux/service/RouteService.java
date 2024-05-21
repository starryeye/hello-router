package dev.practice.usespringwebflux.service;

import dev.practice.usespringwebflux.client.TargetClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteInfoService routeInfoService;
    private final TargetClient targetClient;

    public void route(String issuer, HttpHeaders headers) {


        // 1. proxy 의 region 에 존재하는 origin server 의 url 과 issuer 비교
        if(routeInfoService.compareUrlByRegion(issuer) == Boolean.TRUE) {
            // 2-1. 동일하면 요청 url 을 internal 로 하여 그대로 전송
            String targetUrl = routeInfoService.getCurrentRegionInternalUrl();

        }else {

        }


    }
}
