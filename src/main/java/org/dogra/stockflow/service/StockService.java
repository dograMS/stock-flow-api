package org.dogra.stockflow.service;


import jakarta.validation.Valid;
import org.dogra.stockflow.exception.ResourceNotFound;
import org.dogra.stockflow.model.Stock;
import org.dogra.stockflow.model.dto.StockRequestDTO;
import org.dogra.stockflow.model.dto.StockResponseDTO;
import org.dogra.stockflow.repo.ItemRepo;
import org.dogra.stockflow.repo.PartyRepo;
import org.dogra.stockflow.repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StockService {
    private final StockRepo stockRepo;
    private final ItemRepo itemRepo;
    private final PartyRepo partyRepo;

    @Autowired
    public StockService(StockRepo stockRepo, ItemRepo itemRepo, PartyRepo partyRepo) {
        this.stockRepo = stockRepo;
        this.itemRepo = itemRepo;
        this.partyRepo = partyRepo;
    }

    public StockResponseDTO addNewStock(StockRequestDTO requestDTO) throws ResourceNotFound {
        Stock stock = new Stock();
        stock.setCurrentStock(requestDTO.getCurrentStock());
        stock.setMinimumStock(requestDTO.getMinimumStock());

        stock.setItem(
                itemRepo.findById(requestDTO.getItem_id())
                        .orElseThrow(
                                () -> new ResourceNotFound("Item not found with specified ID : " + requestDTO.getItem_id())
                        )

        );
        stock.setProvider(
                partyRepo.findById(requestDTO.getProvider_id())
                        .orElseThrow(
                                () -> new ResourceNotFound("Provider Party not found with specified ID : " + requestDTO.getProvider_id())
                        )
        );

        stock.setLastUpdated(LocalDateTime.now());

        return new StockResponseDTO(stockRepo.save(stock));
    }


}
