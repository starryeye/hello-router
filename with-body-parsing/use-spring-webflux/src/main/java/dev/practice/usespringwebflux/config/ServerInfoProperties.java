package dev.practice.usespringwebflux.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@Getter
@ConfigurationProperties(prefix = "server-info")
@Validated
public class ServerInfoProperties {

    @NotBlank
    private final String region;

    @ConstructorBinding
    public ServerInfoProperties(String region) {
        this.region = region;
    }
}
