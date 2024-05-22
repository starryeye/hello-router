package dev.practice.usespringwebflux.service;

import dev.practice.usespringwebflux.config.RoutingInfoProperties;
import dev.practice.usespringwebflux.config.ServerInfoProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
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

    public String getHostToConvert(String url) {

        String regex = "^(?:https?://)?([^:/\\s]+)(:\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            String host = matcher.group(1);
            String port = matcher.group(2) != null ? matcher.group(2) : "";

            return host + port;
        } else {
            log.error("Host extract fail.. url = {}", url);
            return "";
        }
    }
}
