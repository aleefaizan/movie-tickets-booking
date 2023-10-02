package com.reel.reserve.controller;

import com.reel.reserve.dto.BookingsDTO;
import com.reel.reserve.exception.ResourceNotFoundException;
import com.reel.reserve.models.Bookings;
import com.reel.reserve.service.BookingsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingsController {
    @Autowired
    private BookingsService bookingsService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<String> createBooking(@Valid @RequestBody BookingsDTO dto) throws ResourceNotFoundException {
        return new ResponseEntity<>(bookingsService.createBooking(dto), HttpStatus.CREATED);
    }
    @GetMapping("/{bookingId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN', 'FRONT_DESK_OFFICER')")
    public ResponseEntity<Bookings> getBooking(@PathVariable Long bookingId) throws ResourceNotFoundException {
        return new ResponseEntity<>(bookingsService.fetchById(bookingId), HttpStatus.OK);
    }
}
