package org.dogra.stockflow.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class StockRequestDTO implements Serializable {

    @NotNull
    private Long item_id;

    @NotNull
    private Long provider_id;

    @Min(value = 0, message = "stock cannot be neagative")
    private Long currentStock;

    @Min(value = 0, message = "stock cannot be neagative")
    private Long minimumStock;

}
