package dev.practice.usespringwebflux.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "server-info")
public class ServerInfoProperties {

    @NotBlank
    private final String region;
}
