package com.team.cinema_app;

import com.team.cinema_app.model.Country;
import com.team.cinema_app.model.Director;
import com.team.cinema_app.model.FilmCompany;
import com.team.cinema_app.model.Genre;
import com.team.cinema_app.repository.CountryRepository;
import com.team.cinema_app.repository.DirectorRepository;
import com.team.cinema_app.repository.FilmCompanyRepository;
import com.team.cinema_app.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class CinemaAppApplication implements CommandLineRunner {

	private final GenreRepository genreRepository;
	private final CountryRepository countryRepository;
	private final DirectorRepository directorRepository;
	private final FilmCompanyRepository filmCompanyRepository;

	public static void main(String[] args) {
		SpringApplication.run(CinemaAppApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (genreRepository.count() == 0) {
			genreRepository.saveAll(List.of(
					new Genre(null, "Action"),
					new Genre(null, "Comedy"),
					new Genre(null, "Drama"),
					new Genre(null, "Horror")
			));
		}

		if (countryRepository.count() == 0) {
			countryRepository.saveAll(List.of(
					new Country(null, "Russia"),
					new Country(null, "USA"),
					new Country(null, "Germany"),
					new Country(null, "France")
			));
		}

		if (directorRepository.count() == 0) {
			directorRepository.saveAll(List.of(
					new Director(null, "Steven Spielberg"),
					new Director(null, "Christopher Nolan"),
					new Director(null, "Quentin Tarantino")
			));
		}

		if (filmCompanyRepository.count() == 0) {
			filmCompanyRepository.saveAll(List.of(
					new FilmCompany(null, "Warner Bros."),
					new FilmCompany(null, "Universal Pictures"),
					new FilmCompany(null, "20th Century Fox")
			));
		}
	}

}
