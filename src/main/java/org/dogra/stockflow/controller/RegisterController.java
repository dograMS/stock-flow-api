package org.dogra.stockflow.controller;

import org.dogra.stockflow.config.User.CatUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@AuthenticationPrincipal CatUserDetails user) {
        return new ResponseEntity<>(user.getUsername(), HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/")
    public ResponseEntity<?> create() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


}
