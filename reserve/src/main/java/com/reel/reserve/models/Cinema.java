package com.reel.reserve.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cinema")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Name must not be null.")
    @NotBlank(message = "Name must not be blank.")
    private String name;

    @NotNull(message = "City must not be null.")
    @NotBlank(message = "City must not be blank.")
    private String city;

}