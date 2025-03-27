package org.dogra.stockflow.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.dogra.stockflow.config.User.CatUserDetails;
import org.dogra.stockflow.model.dto.LoginDTO;
import org.dogra.stockflow.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginData) {

        try {
            return new ResponseEntity<>(registerService.login(loginData), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/new")
    @PreAuthorize(value = "{hasRole('ADMIN')}")
    public ResponseEntity<?> create(@AuthenticationPrincipal CatUserDetails userDetails) {

        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return new ResponseEntity<>("u called : but not authed", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("u called :", HttpStatus.OK);
    }


}
