package org.dogra.stockflow.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.dogra.stockflow.model.TransactionType;

import java.time.LocalDateTime;

public class LogDTO {

    @NotBlank(message = "cannot empty")
    private LocalDateTime logDate;

    @NotBlank
    private Long item_id;

    @NotBlank
    private Long party_id;

    @NotBlank
    private Long staff_id;

    @NotBlank
    @PositiveOrZero(message = "Value cannot be Negative")
    private Long quantity;

    @NotBlank
    @Positive(message = "Value Must be positive")
    private Long unitPrice;

    @NotBlank
    private TransactionType type;

}
