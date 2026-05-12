package com.team.cinema_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "halls")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String title;

    @ManyToOne
    @JoinColumn(name = "hall_type_id")
    @NotNull
    private HallType hallType;

    @NotNull
    private int rows;

    @NotNull
    private int columns;

    @OneToMany(
            mappedBy = "hall",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @NotEmpty
    private List<@NotNull Seat> seats = new ArrayList<>();
}
