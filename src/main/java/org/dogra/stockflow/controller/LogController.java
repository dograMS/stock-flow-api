package org.dogra.stockflow.controller;

import org.dogra.stockflow.model.Log;
import org.dogra.stockflow.service.LogService;
import org.springframework.data.domain.Page;
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

    @GetMapping("/{sortBy}&{page}&{limit}")
    public ResponseEntity<?> getLogPage(@PathVariable String sortBy,
                                        @PathVariable int page, @PathVariable int limit) {
        try {
            Page<Log> pageRequest = logService.getLogs(page, limit, sortBy);

            return new ResponseEntity<>(pageRequest, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> addLog(@RequestBody Log newLog){
        try{
            return new ResponseEntity<>(logService.add(newLog), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Cannot add Log for unknown reason", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> LogById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
