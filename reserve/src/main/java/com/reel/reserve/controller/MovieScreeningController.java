package com.reel.reserve.controller;

import com.reel.reserve.exception.ResourceNotFoundException;
import com.reel.reserve.models.MovieScreening;
import com.reel.reserve.service.MovieScreeningService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("screening-event")
public class MovieScreeningController {

    @Autowired
    private MovieScreeningService screeningService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addScreeningEvent(@Valid @RequestBody MovieScreening screening) throws ResourceNotFoundException {
        return new ResponseEntity<>(screeningService.addMovieScreening(screening), HttpStatus.OK);
    }
}
