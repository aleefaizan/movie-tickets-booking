package com.reel.reserve.service;

import com.reel.reserve.exception.ResourceNotFoundException;
import com.reel.reserve.models.*;
import com.reel.reserve.repository.AuditoriumRepository;
import com.reel.reserve.repository.MovieScreeningRepository;
import com.reel.reserve.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieScreeningService {

    @Autowired
    private MovieScreeningRepository movieScreeningRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private AuditoriumRepository auditoriumRepository;

    public String addMovieScreening(MovieScreening movieScreening) throws ResourceNotFoundException {
        generateSeats(movieScreening);
        movieScreeningRepository.save(movieScreening);
        return "Movie Screen Event added successfully.";
    }

    @Transactional
    private void generateSeats(MovieScreening movieScreening) throws ResourceNotFoundException {
        Auditorium auditorium = auditoriumRepository.findById(movieScreening.getAuditorium().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Auditorium Not Found."));
        Integer numberOfRows = auditorium.getNumRows();
        Integer numberOfColumns = auditorium.getNumCols();
        Character rowCh = 'A';
        for (int row = 1; row < 5; row++) {
            for (int col = 1; col < numberOfColumns; col++) {
                Seat seat = new Seat();
                seat.set_row(rowCh);
                seat.set_col(col);
                seat.setCategory(Category.SILVER);
                seat.setPrice(750.0);
                seat.setStatus(Status.AVAILABLE);
                seat.setMovieScreening(movieScreening);
                seatRepository.save(seat);
            }
            rowCh++;
        }
        for (int row = 5; row < numberOfRows - 2; row++) {
            for (int col = 1; col < numberOfColumns; col++) {
                Seat seat = new Seat();
                seat.set_row(rowCh);
                seat.set_col(col);
                seat.setCategory(Category.GOLD);
                seat.setPrice(850.0);
                seat.setStatus(Status.AVAILABLE);
                seat.setMovieScreening(movieScreening);
                seatRepository.save(seat);
            }
            rowCh++;
        }
        for (int row = numberOfRows - 2; row <= numberOfRows; row++) {
            for (int col = 1; col < numberOfColumns; col++) {
                Seat seat = new Seat();
                seat.set_row(rowCh);
                seat.set_col(col);
                seat.setCategory(Category.PLATINUM);
                seat.setPrice(950.0);
                seat.setStatus(Status.AVAILABLE);
                seat.setMovieScreening(movieScreening);
                seatRepository.save(seat);
            }
            rowCh++;
        }
    }
}
