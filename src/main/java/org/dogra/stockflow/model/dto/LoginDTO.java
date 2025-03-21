package org.dogra.stockflow.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDTO implements Serializable {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
