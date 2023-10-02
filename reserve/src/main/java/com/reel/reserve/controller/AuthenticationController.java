package com.reel.reserve.controller;

import com.reel.reserve.dto.AuthenticationBody;
import com.reel.reserve.dto.JwtAuthenticationResponse;
import com.reel.reserve.dto.RegistrationBody;
import com.reel.reserve.exception.EmailAlreadyRegisteredException;
import com.reel.reserve.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> signup(@Valid @RequestBody RegistrationBody body) throws EmailAlreadyRegisteredException {
        return new ResponseEntity<>(authenticationService.signup(body), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtAuthenticationResponse> signing(@Valid @RequestBody AuthenticationBody body) {
        return new ResponseEntity<>(authenticationService.authenticate(body), HttpStatus.OK);
    }

}
