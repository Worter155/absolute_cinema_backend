package com.team.cinema_app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "film_companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;
}
