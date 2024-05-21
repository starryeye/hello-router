package dev.practice.usespringwebflux.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "routing-info.origin")
public class RoutingInfoProperties {

    @NotBlank
    private String koreaExternalUrl;
    @NotBlank
    private String japanExternalUrl;

    @NotBlank
    private String koreaInternalUrl;
    @NotBlank
    private String japanInternalUrl;
}
