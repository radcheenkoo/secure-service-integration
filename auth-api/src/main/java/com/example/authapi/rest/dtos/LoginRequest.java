package com.example.authapi.rest.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Login request")
public class LoginRequest {


    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 72)
    private String password;

}
