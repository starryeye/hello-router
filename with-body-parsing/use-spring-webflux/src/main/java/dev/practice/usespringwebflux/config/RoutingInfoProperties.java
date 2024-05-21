package dev.practice.usespringwebflux.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@Getter
@ConfigurationProperties(prefix = "routing-info.origin")
@Validated
public class RoutingInfoProperties {

    @NotBlank
    private final String koreaExternalUrl;
    @NotBlank
    private final String japanExternalUrl;

    @NotBlank
    private final String koreaInternalUrl;
    @NotBlank
    private final String japanInternalUrl;

    @ConstructorBinding
    public RoutingInfoProperties(String koreaExternalUrl, String japanExternalUrl, String koreaInternalUrl, String japanInternalUrl) {
        this.koreaExternalUrl = koreaExternalUrl;
        this.japanExternalUrl = japanExternalUrl;
        this.koreaInternalUrl = koreaInternalUrl;
        this.japanInternalUrl = japanInternalUrl;
    }
}
