package com.example.authapi.rest.controllers;


import com.example.authapi.data.services.ProcessingService;
import com.example.authapi.rest.dtos.ProcessRequest;
import com.example.authapi.rest.dtos.TransformResponse;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/process")
@RequiredArgsConstructor
public class ProcessingController {

    private final ProcessingService processingService;

    @PostMapping
    public ResponseEntity<TransformResponse> process(
            @Valid @RequestBody ProcessRequest request,
            Authentication authentication) {

        TransformResponse response =
                processingService.process(request.getText(), authentication.getName());

        return ResponseEntity.ok(response);
    }


}
