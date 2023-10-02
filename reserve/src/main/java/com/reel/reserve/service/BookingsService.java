package com.reel.reserve.service;

import com.reel.reserve.dto.BookingsDTO;
import com.reel.reserve.exception.ResourceNotFoundException;
import com.reel.reserve.models.*;
import com.reel.reserve.repository.BookingsRepository;
import com.reel.reserve.repository.MovieScreeningRepository;
import com.reel.reserve.repository.SeatRepository;
import com.reel.reserve.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookingsService {

    @Autowired
    private BookingsRepository bookingsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private MovieScreeningRepository movieScreeningRepository;
    public String createBooking(BookingsDTO dto) throws ResourceNotFoundException {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found."));
        MovieScreening screening = movieScreeningRepository.findById(dto.getScreeningId())
                .orElseThrow(() -> new ResourceNotFoundException("Screening Not Found."));
        List<Seat> seats = seatRepository.findAllById(dto.getSeatIds());

        Bookings bookings = new Bookings();
        bookings.setName(user.getName());
        bookings.setEmail(user.getEmail());
        bookings.setContact(user.getContact());
        bookings.setSeats(seats);
        bookings.setMovieScreening(screening);
        bookingsRepository.save(bookings);
        updateSeatDetails(seats, bookings);
        return "Booking Created Successfully.";

    }

    public Bookings fetchById(Long bookingId) throws ResourceNotFoundException {
        return bookingsRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking Not Found."));
    }
    private void updateSeatDetails(List<Seat> seats, Bookings bookings) {
        for (Seat seat: seats) {
            seat.setBooking(bookings);
            seat.setStatus(Status.RESERVED);
            seatRepository.save(seat);
        }
    }

}
