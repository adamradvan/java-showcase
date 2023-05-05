package io.radvan.adam.showcase.sync;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.synchronization", ignoreUnknownFields = false)
// beaned in SyncConfiguration
public class SyncConfigurationProperties {

    /**
     * Flag enabling/disabling synchronization
     */
    private boolean enabled = false;

    private TeamsProperties teams = new TeamsProperties();
    private ApiProperties api = new ApiProperties();

    /**
     * Connection timeout value
     */
    private Duration connectTimeout = Duration.ofSeconds(10);

    /**
     * Read timeout value
     */
    private Duration readTimeout = Duration.ofSeconds(60);

    @Getter
    @Setter
    public static class TeamsProperties {

        /**
         * CRON expression for schedule
         */
        private String cron = "-";

    }

    @Getter
    @Setter
    public static class ApiProperties {

        /**
         * API Key for calling REST API (X-RapidAPI-Key)
         */
        @NotBlank
        private String key;

        /**
         * API Host for calling REST API (X-RapidAPI-Host)
         */
        @NotBlank
        private String host;

    }

}
