package com.reel.reserve.controller;

import com.reel.reserve.dto.DeskOfficerAuthenticationBody;
import com.reel.reserve.dto.FrontDeskOfficerRegistrationBody;
import com.reel.reserve.dto.JwtAuthenticationResponse;
import com.reel.reserve.exception.EmailAlreadyRegisteredException;
import com.reel.reserve.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("desk-officer")
public class DeskOfficerController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JwtAuthenticationResponse> register(@Valid @RequestBody FrontDeskOfficerRegistrationBody body) throws EmailAlreadyRegisteredException {
        return new ResponseEntity<>(authenticationService.register(body), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtAuthenticationResponse> authenticate(@Valid @RequestBody DeskOfficerAuthenticationBody body) {
        return new ResponseEntity<>(authenticationService.authenticateDeskOfficer(body), HttpStatus.OK);
    }

}
