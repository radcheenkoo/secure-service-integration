package com.example.authapi.rest.clients;

import com.example.authapi.rest.dtos.ProcessRequest;
import com.example.authapi.rest.dtos.TransformResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DataApiClient {

    private final RestClient restClient;
    private final String internalToken;

    public DataApiClient(
            RestClient.Builder builder,
            @Value("${data-api.base-url}") String baseUrl,
            @Value("${data-api.internal-token}") String internalToken) {
        this.restClient = builder
                .baseUrl(baseUrl)
                .build();

        this.internalToken = internalToken;
    }

    public TransformResponse transform(String text) {
        return restClient.post()
                .uri("/api/transform")
                .header("X-Internal-Token", internalToken)
                .body(new ProcessRequest(text))
                .retrieve()
                .body(TransformResponse.class);
    }
}

