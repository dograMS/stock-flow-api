package org.dogra.stockflow.controller;


import jakarta.validation.Valid;
import org.dogra.stockflow.model.Party;
import org.dogra.stockflow.model.dto.ErrorResponse;
import org.dogra.stockflow.service.PartyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/party")
public class PartyController {

    private PartyService partyService;

    public PartyController(PartyService partyService){
        this.partyService = partyService;
    }

    @PostMapping("/")
    public ResponseEntity<?> addParty(@RequestBody @Valid Party party){
        try{
            return new ResponseEntity<>( partyService.add(party), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
