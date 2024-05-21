package dev.practice.usespringwebflux.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@Getter
@ConfigurationProperties(prefix = "routing-info.origin")
@Validated
public class RoutingInfoProperties {

    @NotNull
    private final InternalUrls internal;

    @NotNull
    private final ExternalUrls external;

    @ConstructorBinding
    public RoutingInfoProperties(InternalUrls internal, ExternalUrls external) {
        this.internal = internal;
        this.external = external;
    }

    @Getter
    public static class InternalUrls {
        @NotBlank
        private final String korea;
        @NotBlank
        private final String japan;

        @ConstructorBinding
        public InternalUrls(String korea, String japan) {
            this.korea = korea;
            this.japan = japan;
        }
    }

    @Getter
    public static class ExternalUrls {
        @NotBlank
        private final String korea;
        @NotBlank
        private final String japan;

        @ConstructorBinding
        public ExternalUrls(String korea, String japan) {
            this.korea = korea;
            this.japan = japan;
        }
    }
}
