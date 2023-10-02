package com.reel.reserve.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Character _row;
    private Integer _col;
    private Category category;
    private Double price;
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_screening_id")
    private MovieScreening movieScreening;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Bookings booking;

}