package org.dogra.stockflow.controller;


import jakarta.validation.Valid;
import org.dogra.stockflow.model.Party;
import org.dogra.stockflow.model.dto.ErrorResponse;
import org.dogra.stockflow.model.dto.PageResponseDTO;
import org.dogra.stockflow.service.PartyService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{page}&{size}")
    public ResponseEntity<?> getAllAvailableParties(@PathVariable int page, @PathVariable int size){

        return new ResponseEntity<>(new PageResponseDTO(partyService.getParties(page, size)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getParty(@PathVariable Long id){

        try{
            return new ResponseEntity<>(partyService.findParty(id), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParty(@PathVariable Long id){

        try{
            partyService.deletePartyByID(id);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>( HttpStatus.OK);
    }




}
