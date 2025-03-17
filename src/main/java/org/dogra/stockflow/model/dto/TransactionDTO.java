package org.dogra.stockflow.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class TransactionDTO implements Serializable {
    @NotNull
    private Long stockId;
    @NotNull
    @Min(value = 1, message = "Quantity Must be more than 1 to perform any transaction")
    private Long quantity;
    @NotNull
    @Min(value = 0, message = "Every item must have Non-negative price")
    private Long unitPrice;
    @NotNull
    private Long partyID;
}
