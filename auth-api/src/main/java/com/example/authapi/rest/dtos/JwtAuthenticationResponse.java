package com.example.authapi.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response with jwt token")
public class JwtAuthenticationResponse {

    @Schema(description = "jwt token")
    private String token;

}
