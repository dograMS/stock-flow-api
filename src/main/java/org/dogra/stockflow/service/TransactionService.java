package org.dogra.stockflow.service;

import io.swagger.v3.core.util.Json;
import jakarta.validation.ConstraintViolation;
import org.dogra.stockflow.model.Log;
import org.dogra.stockflow.model.Staff;
import org.dogra.stockflow.model.Stock;
import org.dogra.stockflow.model.TransactionType;
import org.dogra.stockflow.model.dto.ErrorResponse;
import org.dogra.stockflow.model.dto.StockRequestDTO;
import org.dogra.stockflow.model.dto.StockResponseDTO;
import org.dogra.stockflow.model.dto.TransactionDTO;
import org.dogra.stockflow.repo.StockRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Set;

@Service
public class TransactionService {
    private final LogService logService;
    private final StockRepo stockRepo;

    private final Validator validator;

    public TransactionService(LogService logService, StockRepo stockRepo, Validator validator){
        this.logService = logService;
        this.stockRepo = stockRepo;
        this.validator = validator;
    }

    @Transactional
    public StockResponseDTO saleItemAndLog(Staff staff, TransactionDTO transactionDetails) throws Exception {
        Stock stock = stockRepo.findById(transactionDetails.getStockId())
                .orElseThrow(
                        () -> new Exception("Stock not found with ID:" + transactionDetails.getStockId())
                );
        
        stock.setCurrentStock(stock.getCurrentStock() - transactionDetails.getQuantity());
        Errors errors = validator.validateObject(stock);
        if(errors.hasErrors()){
            var res = new ErrorResponse("Stock Management Validations failed", errors);
            throw new Exception(Json.pretty(res));
        }

        logService.add( new Log(
                LocalDateTime.now(),
                stock.getItem(),
                stock.getProvider(),
                staff,
                transactionDetails.getQuantity(),
                transactionDetails.getUnitPrice(),
                TransactionType.SALE
        ));

        return new StockResponseDTO(stockRepo.save(stock));
    }

    public StockResponseDTO purchaseItemAndLog(Staff staff, TransactionDTO transactionDetails){
        Stock stock = stockRepo.findById(transactionDetails.getStockId()).get();

        return new StockResponseDTO(stock);
    }


    public StockResponseDTO restockItemAndLog(Staff staff, TransactionDTO transactionDetails){
        Stock stock = stockRepo.findById(transactionDetails.getStockId()).get();

        return new StockResponseDTO(stock);
    }




}
