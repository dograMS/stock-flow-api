package org.dogra.stockflow.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
public class ErrorResponse implements Serializable {
    private final String message;
    private final Map<String, String> errorFields;

    public ErrorResponse(String message){
        this.message = message;
        this.errorFields = null;
    }

    public ErrorResponse(String message, Errors errors){
        this.message = message;
        this.errorFields = new HashMap<>();
        errors.getFieldErrors().forEach(
                fieldError -> errorFields.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
    }
}
