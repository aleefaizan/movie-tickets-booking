package com.reel.reserve.service;

import com.reel.reserve.exception.ResourceNotFoundException;
import com.reel.reserve.models.Movie;
import com.reel.reserve.repository.MovieRepository;
import com.reel.reserve.respone.MovieResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ModelMapper modelMapper;

    public String addMovie(Movie movie) {
        movieRepository.save(movie);
        return "Movie added successfully.";
    }

    public MovieResponse searchByTitle(String title, Integer pageNum, String sortBy, Integer pageSize) {
        Sort sort = Sort.by(sortBy);
        Pageable p = PageRequest.of(pageNum, pageSize, sort);
        Page<Movie> moviePage = movieRepository.findByTitle(title, p);

        List<Movie> movies = moviePage.getContent();
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setMovies(movies);
        movieResponse.setPageNum(pageNum);
        movieResponse.setPageSize(pageSize);
        movieResponse.setTotalPage(moviePage.getTotalPages());
        movieResponse.setTotalElement((int)moviePage.getTotalElements());
        movieResponse.setLastPage(moviePage.isLast());
        return movieResponse;
    }

    public String deleteMovie(Long movieId) throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie doesn't exist."));
        movieRepository.delete(movie);
        return "Movie Deleted Successfully.";
    }
}
