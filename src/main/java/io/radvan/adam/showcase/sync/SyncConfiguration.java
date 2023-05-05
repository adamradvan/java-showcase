package io.radvan.adam.showcase.sync;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
public class SyncConfiguration {

    public static final String RAPID_API_NBA_REST_TEMPLATE = "RapidApiNbaRestTemplate";

    @Bean
    public SyncConfigurationProperties syncConfigurationProperties() {
        return new SyncConfigurationProperties();
    }

    @Bean
    @Qualifier(RAPID_API_NBA_REST_TEMPLATE)
    public RestTemplate restTemplate(RestTemplateBuilder builder, SyncConfigurationProperties properties) {
        RestTemplateBuilder restTemplateBuilder = builder
                .defaultHeader("X-RapidAPI-Key",  properties.getApi().getKey())
                .defaultHeader("X-RapidAPI-Host", properties.getApi().getHost())
                .setConnectTimeout(properties.getConnectTimeout())
                .setReadTimeout(properties.getReadTimeout())
                .errorHandler(new ResponseErrorHandler() {
                    @Override
                    public boolean hasError(@NonNull ClientHttpResponse response) throws IOException {
                        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
                    }

                    @Override
                    public void handleError(@NonNull ClientHttpResponse response) throws IOException {
                        // return as response entity instead of throwing an exception
                        new ResponseEntity<>(response.getBody(), response.getStatusCode());
                    }
                });

        return restTemplateBuilder.build();
    }

    @Bean
    public SyncService syncService(RestTemplate template, ObjectMapper objectMapper) {
        return new SyncService(template, objectMapper);
    }

}
