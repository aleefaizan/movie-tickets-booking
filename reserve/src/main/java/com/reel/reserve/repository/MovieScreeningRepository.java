package com.reel.reserve.repository;

import com.reel.reserve.models.MovieScreening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieScreeningRepository extends JpaRepository<MovieScreening, Long> {

    @Query(value = "delete * from seat where movie_screening_id = ?1", nativeQuery = true)
    void deleteSeats(Long movieScreeningId);
}
