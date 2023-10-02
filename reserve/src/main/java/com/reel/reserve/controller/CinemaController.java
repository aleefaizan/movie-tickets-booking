package com.reel.reserve.controller;

import com.reel.reserve.exception.ResourceNotFoundException;
import com.reel.reserve.models.Cinema;
import com.reel.reserve.service.CinemaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addCinema(@Valid @RequestBody Cinema cinema) {
        return new ResponseEntity<>(cinemaService.addCinema(cinema), HttpStatus.OK);
    }

    @DeleteMapping("/{cinemaId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> removeCinema(@PathVariable Long cinemaId) throws ResourceNotFoundException {
        return new ResponseEntity<>(cinemaService.deleteCinema(cinemaId), HttpStatus.OK);
    }
}
