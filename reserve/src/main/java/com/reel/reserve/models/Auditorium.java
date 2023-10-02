package com.reel.reserve.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "auditorium")
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Name must not be null.")
    @NotBlank(message = "Name must not be blank.")
    private String name;

    @NotNull(message = "Number of Rows must not be null.")
    private Integer numRows;

    @NotNull(message = "Number of Columns must not be null.")
    private Integer numCols;

    @NotNull(message = "Auditorium must have a cinema.")
    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

}