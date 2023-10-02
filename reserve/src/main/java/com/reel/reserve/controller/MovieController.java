package com.reel.reserve.controller;

import com.reel.reserve.exception.ResourceNotFoundException;
import com.reel.reserve.models.Movie;
import com.reel.reserve.respone.MovieResponse;
import com.reel.reserve.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addMovie(@Valid @RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.addMovie(movie), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'FRONT_DESK_OFFICER')")
    public MovieResponse searchByTitle(
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam String title) {
        return new ResponseEntity<MovieResponse>(movieService.searchByTitle(title, pageNum, sortBy, pageSize), HttpStatus.OK).getBody();
    }

    @DeleteMapping("/{movieId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> removeMovie(@PathVariable Long movieId) throws ResourceNotFoundException {
        return new ResponseEntity<>(movieService.deleteMovie(movieId), HttpStatus.OK);
    }
}
