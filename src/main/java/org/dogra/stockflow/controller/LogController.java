package org.dogra.stockflow.controller;

import org.dogra.stockflow.model.Log;
import org.dogra.stockflow.model.dto.PageResponseDTO;
import org.dogra.stockflow.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/log")
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/page/{page}&{limit}")
    public ResponseEntity<?> getLogPage(@PathVariable int page, @PathVariable int limit) {
        PageResponseDTO<?> pageRes = logService.getLogs(page, limit);
        return new ResponseEntity<>(pageRes, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> LogById(@PathVariable Long id){
        try{
            return new ResponseEntity<>( logService.findLongById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
