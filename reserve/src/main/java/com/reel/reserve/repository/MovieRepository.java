package com.reel.reserve.repository;

import com.reel.reserve.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
    Page<Movie> findByTitle(String title, Pageable pageable);
}
