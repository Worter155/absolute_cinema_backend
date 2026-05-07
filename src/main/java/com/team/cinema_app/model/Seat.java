package com.team.cinema_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Table(name = "seats",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_hall_row_column",
                        columnNames = {"hall_id", "seat_row", "seat_column"}
                )
        }
)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "seat_type_id")
    private SeatType seatType;

    @NotNull
    private int seatRow;

    @NotNull
    private int seatColumn;
}
