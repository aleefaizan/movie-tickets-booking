package com.reel.reserve.service;

import com.reel.reserve.exception.ResourceNotFoundException;
import com.reel.reserve.models.Cinema;
import com.reel.reserve.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    public String addCinema(Cinema cinema) {
        cinemaRepository.save(cinema);
        return "Cinema added successfully";
    }
    public String deleteCinema(Long cinemaId) throws ResourceNotFoundException {
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cinema Doesn't exist."));
        cinemaRepository.delete(cinema);
        return "Cinema Delete Successfully";
    }
}
