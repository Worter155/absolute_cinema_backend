package com.team.cinema_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "sessions")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private double basePrice;
}
