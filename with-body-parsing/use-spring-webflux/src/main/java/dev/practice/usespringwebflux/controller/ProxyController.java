package dev.practice.usespringwebflux.controller;

import dev.practice.usespringwebflux.service.RouteService;
import dev.practice.usespringwebflux.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Controller
@RequestMapping("/proxy")
@RequiredArgsConstructor
public class ProxyController {

    private final TokenService tokenService;
    private final RouteService routeService;

    @PostMapping("/request")
    public Mono<ResponseEntity<String>> request(
            @RequestBody String body,
            @RequestHeader HttpHeaders headers,
            @RequestParam Map<String, String> parameters
    ) {

        String token = tokenService.extractToken(body);
        String issuer = tokenService.getIssuerFromToken(token);


    }
}
