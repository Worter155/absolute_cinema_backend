package com.team.cinema_app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "film_companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String title;
}
