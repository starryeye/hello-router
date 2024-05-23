package dev.practice.usespringwebflux.controller;

import dev.practice.usespringwebflux.controller.request.RouteRequest;
import dev.practice.usespringwebflux.service.RouteService;
import dev.practice.usespringwebflux.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/proxy")
@RequiredArgsConstructor
public class RouteController {

    private final TokenService tokenService;
    private final RouteService routeService;

    @PostMapping(value = "/request", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Mono<ResponseEntity<String>> request(
            @ModelAttribute RouteRequest request,
            @RequestHeader HttpHeaders headers
    ) {

//        String token = tokenService.extractToken(body);

        String token = request.token();
        String body = request.getRequestBody();

        String issuer = tokenService.getIssuerFromToken(token);

        return routeService.route(issuer, headers, body);
    }
}
