package com.team.cinema_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String title;

    @NotNull
    private Integer duration;

    @NotNull
    private Integer ageLimit;

    private String posterPath;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @NotNull
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @NotNull
    private Country country;

    @ManyToOne
    @JoinColumn(name = "director_id")
    @NotNull
    private Director director;

    @ManyToOne
    @JoinColumn(name = "film_company_id")
    @NotNull
    private FilmCompany filmCompany;
}
