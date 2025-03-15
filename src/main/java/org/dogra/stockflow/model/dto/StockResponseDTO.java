package org.dogra.stockflow.model.dto;

import lombok.Data;
import org.dogra.stockflow.model.Stock;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class StockResponseDTO implements Serializable {

    private Long id;
    private String itemName;
    private Long baseUnitPrice;
    private String providerName;
    private String company;
    private Long currentStock;
    private Long minimumStock;
    private LocalDateTime lastUpdated;



    public StockResponseDTO(Stock stock) {

        this.id = stock.getId();
        this.itemName = stock.getItem().getName();
        this.baseUnitPrice = stock.getItem().getBaseUnitPrice();
        this.providerName = stock.getProvider().getName();
        this.company = stock.getProvider().getCompany();
        this.currentStock = stock.getCurrentStock();
        this.minimumStock = stock.getMinimumStock();
        this.lastUpdated = stock.getLastUpdated();

    }
}
