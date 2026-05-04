package com.team.cinema_app.service;

import com.team.cinema_app.dto.MovieRequest;
import com.team.cinema_app.dto.MovieResponse;
import com.team.cinema_app.exception.*;
import com.team.cinema_app.mapper.MovieMapper;
import com.team.cinema_app.model.*;
import com.team.cinema_app.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;
    private  final DirectorRepository directorRepository;
    private final FilmCompanyRepository filmCompanyRepository;
    private final MovieMapper mapper;

    private final FileStorageService fileStorageService;

    public MovieResponse createMovie(MovieRequest request){

        Genre genre = genreRepository.findById(UUID.fromString(request.getGenreId()))
                .orElseThrow(() -> new GenreNotFoundException("Жанр не найден с id " + request.getGenreId()));

        Country country = countryRepository.findById(UUID.fromString(request.getCountryId()))
                .orElseThrow(() -> new CountryNotFoundException("Страна не найдена с id " + request.getCountryId()));

        Director director = directorRepository.findById(UUID.fromString(request.getDirectorId()))
                .orElseThrow(() -> new DirectorNotFoundException("Режиссер не найден с id " + request.getDirectorId()));

        FilmCompany filmCompany = filmCompanyRepository.findById(UUID.fromString(request.getFilmCompanyId()))
                .orElseThrow(() -> new FilmCompanyNotFoundException("Кинокомпания не найдена с id " + request.getFilmCompanyId()));

        Movie movie = movieRepository.save(mapper.toEntity(request, genre, country, director, filmCompany));

        return mapper.toResponse(movie);
    }

    public List<MovieResponse> getAllMovies(){
        return movieRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public MovieResponse getMovieById(UUID id){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Фильм не найден c id " + id));
        return mapper.toResponse(movie);
    }

    public MovieResponse updateMovieById(UUID id, MovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Фильм не найден с id " + id));

        Genre genre = genreRepository.findById(UUID.fromString(request.getGenreId()))
                .orElseThrow(() -> new GenreNotFoundException("Жанр не найден с id " + request.getGenreId()));

        Country country = countryRepository.findById(UUID.fromString(request.getCountryId()))
                .orElseThrow(() -> new CountryNotFoundException("Страна не найдена с id " + request.getCountryId()));

        Director director = directorRepository.findById(UUID.fromString(request.getDirectorId()))
                .orElseThrow(() -> new DirectorNotFoundException("Режиссер не найден с id " + request.getCountryId()));

        FilmCompany filmCompany = filmCompanyRepository.findById(UUID.fromString(request.getFilmCompanyId()))
                .orElseThrow(() -> new FilmCompanyNotFoundException("Кинокомпания не найдена с id " + request.getCountryId()));

        mapper.updateEntity(movie, request, genre, country, director, filmCompany);

        Movie updated = movieRepository.save(movie);

        return mapper.toResponse(updated);
    }

    public void deleteMovie(UUID id){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Фильм не найден c id " + id));

        if (movie.getPosterPath() != null) {
            fileStorageService.deletePoster(movie.getPosterPath());
        }

        movieRepository.delete(movie);
    }

    public void uploadPoster(UUID id, MultipartFile file) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Фильм не найден c id " + id));

        if (movie.getPosterPath() != null) {
            fileStorageService.deletePoster(movie.getPosterPath());
        }

        String path = fileStorageService.savePoster(id, file);

        movie.setPosterPath(path);
        movieRepository.save(movie);
    }

    public Movie findEntityById(UUID id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Фильм не найден c id " + id));
    }
}
