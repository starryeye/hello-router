package dev.practice.usespringwebflux.controller.request;

public record RouteRequest(
        String token
) {

    public String getRequestBody() {
        return "token=" + token;
    }
}
