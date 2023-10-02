package com.reel.reserve.controller;

import com.reel.reserve.exception.ResourceNotFoundException;
import com.reel.reserve.models.Auditorium;
import com.reel.reserve.service.AuditoriumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auditoriums")
public class AuditoriumController {

    @Autowired
    private AuditoriumService auditoriumService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addAuditorium(@Valid @RequestBody Auditorium auditorium) {
        return new ResponseEntity<>(auditoriumService.addAuditorium(auditorium), HttpStatus.OK);
    }

    @DeleteMapping("/{auditoriumId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> removeAuditorium(@PathVariable Long auditoriumId) throws ResourceNotFoundException {
        return new ResponseEntity<>(auditoriumService.deleteAuditorium(auditoriumId), HttpStatus.OK);
    }
}
