package org.dogra.stockflow.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LoginResponseDTO implements Serializable {
    private String username;
    private List<String> roles;
    private String tokens;

}
