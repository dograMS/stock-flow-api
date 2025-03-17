package org.dogra.stockflow.controller;

import jakarta.validation.Valid;
import org.dogra.stockflow.config.User.CatUserDetails;
import org.dogra.stockflow.model.dto.TransactionDTO;
import org.dogra.stockflow.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tp")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/sale")
    public ResponseEntity<?> saleItem(@AuthenticationPrincipal CatUserDetails userDetails,
                                      @Valid TransactionDTO transactionDetails) {

        try {
            return new ResponseEntity<>(
                    transactionService.saleItemAndLog(userDetails.getStaffMember(), transactionDetails), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseItem() {

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @PostMapping("/restock")
    public ResponseEntity<?> restockItem() {

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
