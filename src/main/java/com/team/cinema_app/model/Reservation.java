package com.team.cinema_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "reservations")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "session_id")
    @NotNull
    private Session session;

    @ManyToMany
    @JoinTable(
            name = "reservation_seats",

            joinColumns = @JoinColumn(
                    name = "reservation_id"
            ),

            inverseJoinColumns = @JoinColumn(
                    name = "seat_id"
            )
    )
    @NotNull
    private List<Seat> seats = new ArrayList<>();;

    @NotNull
    private double price;
}
