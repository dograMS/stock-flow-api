package org.dogra.stockflow.controller;

import org.dogra.stockflow.model.Item;
import org.dogra.stockflow.model.dto.ItemDTO;
import org.dogra.stockflow.model.dto.PageResponseDTO;
import org.dogra.stockflow.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
class ItemController {

    Logger logger = LoggerFactory.getLogger(ItemController.class);

    ItemService itemService;

    ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/{q}&{page}&{size}")
    ResponseEntity<?> SearchItem(@PathVariable String q, @PathVariable int page, @PathVariable int size){

        Page<Item> pageRes = itemService.searchItem(size, page, q);

    return new ResponseEntity<>(PageResponseDTO.of(pageRes), HttpStatus.OK);
    }




    @PostMapping("/")
    ResponseEntity<?> addItem(@Validated @RequestBody ItemDTO itemInfo) {

        try {
            return new ResponseEntity<>(itemService.add(itemInfo.getItem(),
                    itemInfo.getItemProviderIdsList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteItem(@RequestParam Long id) {
        itemService.removeItem(List.of(id));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/list")
    ResponseEntity<?> deleteItem(@RequestParam List<Long> ids) {
        itemService.removeItem(ids);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/id")
    ResponseEntity<?> updateItem(@RequestParam Long id , @RequestBody ItemDTO itemDetails) {

        try {
            return new ResponseEntity<>(
                    itemService.updateItem(id, itemDetails),
                    HttpStatus.OK
            );
        } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }



}
