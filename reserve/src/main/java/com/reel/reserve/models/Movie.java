package com.reel.reserve.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private Long id;

    @NotNull(message = "Title must not be null.")
    @NotBlank(message = "Title must not be blank.")
    private String title;
    private String description;
    @NotNull(message = "Language must not be null.")
    @NotBlank(message = "Language must not be blank.")
    private String language;
    private Date releaseDate;

}