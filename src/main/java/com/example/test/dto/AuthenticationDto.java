package com.example.test.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
public class AuthenticationDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 3, max = 8, message = "Password must be between 3 and 8 characters")
    private String password;
}
