package dev.practice.usespringwebflux.service;

import dev.practice.usespringwebflux.config.RoutingInfoProperties;
import dev.practice.usespringwebflux.config.ServerInfoProperties;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RouteInfoService {

    private final RoutingInfoProperties routingInfoProperties;
    private final ServerInfoProperties serverInfoProperties;
    private final Set<String> externalUrls = new HashSet<>();

    public RouteInfoService(RoutingInfoProperties routingInfoProperties, ServerInfoProperties serverInfoProperties) {

        this.routingInfoProperties = routingInfoProperties;
        this.serverInfoProperties = serverInfoProperties;

        externalUrls.add(routingInfoProperties.getJapanExternalUrl());
        externalUrls.add(routingInfoProperties.getKoreaExternalUrl());
    }

    public Boolean compareUrlByRegion(String url) {

        if(isValidUrl(url) == Boolean.FALSE)
            throw new IllegalArgumentException("Invalid URL"); // todo, 4xx

        return url.equals(getCurrentRegionExternalUrl());
    }

    public String getCurrentRegionInternalUrl() {

        return switch (serverInfoProperties.getRegion()) {
            case "korea" -> routingInfoProperties.getKoreaInternalUrl();
            case "japan" -> routingInfoProperties.getJapanInternalUrl();
            default -> throw new IllegalStateException("Unexpected value: " + serverInfoProperties.getRegion());
        };
    }

    public String getCurrentRegionExternalUrl() {

        return switch (serverInfoProperties.getRegion()) {
            case "korea" -> routingInfoProperties.getKoreaExternalUrl();
            case "japan" -> routingInfoProperties.getJapanExternalUrl();
            default -> throw new IllegalStateException("Unexpected value: " + serverInfoProperties.getRegion());
        };
    }

    private Boolean isValidUrl(String url) {
        return externalUrls.contains(url);
    }
}
