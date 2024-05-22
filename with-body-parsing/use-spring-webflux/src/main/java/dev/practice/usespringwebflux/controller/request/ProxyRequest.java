package dev.practice.usespringwebflux.controller.request;

public record ProxyRequest(
        String token
) {

    public String getRequestBody() {
        return "token=" + token;
    }
}
