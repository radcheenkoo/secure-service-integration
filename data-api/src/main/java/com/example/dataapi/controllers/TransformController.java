package com.example.dataapi.controllers;

import com.example.dataapi.dtos.TransformRequest;
import com.example.dataapi.dtos.TransformResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transform")
public class TransformController {

    private final String internalToken;

    public TransformController(
            @Value("${internal.token:}") String internalToken) {
        this.internalToken = internalToken;
    }

    @PostMapping
    public ResponseEntity<TransformResponse> transform(
            @RequestHeader(
                    value = "X-Internal-Token",
                    required = false
            ) String token,
            @RequestBody TransformRequest request) {

        if (internalToken.isBlank()
                || token == null
                || !java.security.MessageDigest.isEqual(
                token.getBytes(java.nio.charset.StandardCharsets.UTF_8),
                internalToken.getBytes(java.nio.charset.StandardCharsets.UTF_8))) {
            return ResponseEntity.status(403).build();
        }

        String result = request.getText().toUpperCase();

        return ResponseEntity.ok(new TransformResponse(result));
    }
}
