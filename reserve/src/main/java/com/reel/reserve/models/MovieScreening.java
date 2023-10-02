package com.reel.reserve.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@Entity
@Table(name = "movie_screening")
public class MovieScreening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Movie Screening must have a movie.")
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @NotNull(message = "Time must not be null.")
    private Time time;
    @NotNull(message = "Date must not be null.")
    private Date date;
    @NotNull(message = "Screening must have auditorium.")
    @ManyToOne
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

}