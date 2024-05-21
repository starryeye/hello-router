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

        externalUrls.add(routingInfoProperties.getExternal().getJapan());
        externalUrls.add(routingInfoProperties.getExternal().getKorea());
    }

    public Boolean compareUrlByRegion(String url) {

        return url.equals(getCurrentRegionExternalUrl());
    }

    public String getCurrentRegionInternalUrl() {

        return switch (serverInfoProperties.getRegion()) {
            case "korea" -> routingInfoProperties.getInternal().getKorea();
            case "japan" -> routingInfoProperties.getInternal().getJapan();
            default -> throw new IllegalStateException("Unexpected value: " + serverInfoProperties.getRegion());
        };
    }

    public String getCurrentRegionExternalUrl() {

        return switch (serverInfoProperties.getRegion()) {
            case "korea" -> routingInfoProperties.getExternal().getKorea();
            case "japan" -> routingInfoProperties.getExternal().getJapan();
            default -> throw new IllegalStateException("Unexpected value: " + serverInfoProperties.getRegion());
        };
    }

    public Boolean isValidUrl(String url) {

        return externalUrls.contains(url);
    }
}
