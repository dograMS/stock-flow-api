package org.dogra.stockflow.controller;

import jakarta.validation.Valid;
import org.dogra.stockflow.model.Item;
import org.dogra.stockflow.model.dto.ItemDTO;
import org.dogra.stockflow.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
class ItemController {

    Logger logger = LoggerFactory.getLogger(ItemController.class);

    ItemService itemService;

    ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping("/")
    ResponseEntity<?> addItem(@Validated @RequestBody ItemDTO itemInfo){
        itemService.add(itemInfo.getItem(), itemInfo.getItemProviderIdsList());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> addItem(@RequestParam Long id){
        itemService.removeItem(List.of(id));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/list")
    ResponseEntity<?> addItem(@RequestParam List<Long> ids){
        itemService.removeItem(ids);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidMethodArgumentExcetpion(MethodArgumentNotValidException e) {
        Map<String, String> errosMap = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(fieldError ->
                errosMap.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return new ResponseEntity<>(errosMap, HttpStatus.BAD_REQUEST);
    }
}
