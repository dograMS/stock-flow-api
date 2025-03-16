package org.dogra.stockflow.controller;

import org.dogra.stockflow.model.dto.PageResponseDTO;
import org.dogra.stockflow.model.dto.StockRequestDTO;
import org.dogra.stockflow.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final Logger logger = LoggerFactory.getLogger(StockController.class);

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/")
    public ResponseEntity<?> addNewStock(@RequestBody StockRequestDTO stockRequestDTO) {

        try {
            return new ResponseEntity<>(stockService.addNewStock(stockRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStockEntry(@PathVariable Long id){
        try{
            stockService.deleteLog(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{page}&{size}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PageResponseDTO<?> getStockList(@PathVariable int page, @PathVariable int size){

        return stockService.stockPages(page, size);

    }


}
