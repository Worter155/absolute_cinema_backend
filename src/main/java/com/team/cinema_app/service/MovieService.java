package com.team.cinema_app.service;

import com.team.cinema_app.dto.MovieRequest;
import com.team.cinema_app.dto.MovieResponse;
import com.team.cinema_app.mapper.MovieMapper;
import com.team.cinema_app.model.*;
import com.team.cinema_app.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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

        Genre genre = genreRepository.findById(request.getGenreId())
                .orElseThrow(() -> new RuntimeException("Жанр не найден"));

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new RuntimeException("Страна не найдена"));

        Director director = directorRepository.findById(request.getDirectorId())
                .orElseThrow(() -> new RuntimeException("Режиссер не найден"));

        FilmCompany filmCompany = filmCompanyRepository.findById(request.getFilmCompanyId())
                .orElseThrow(() -> new RuntimeException("Кинокомпания не найдена"));

        Movie movie = mapper.toEntity(request, genre, country, director, filmCompany);

        movieRepository.save(movie);

        return mapper.toResponse(movie);
    }

    public List<MovieResponse> getAllMovies(){
        return movieRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public MovieResponse getMovieById(Long id){
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Фильм не найден"));
        return mapper.toResponse(movie);
    }

    public MovieResponse updateMovieById(Long id, MovieRequest request){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Фильм не найден"));

        Genre genre = genreRepository.findById(request.getGenreId())
                .orElseThrow(() -> new RuntimeException("Жанр не найден"));

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new RuntimeException("Страна не найдена"));

        Director director = directorRepository.findById(request.getDirectorId())
                .orElseThrow(() -> new RuntimeException("Режиссер не найден"));

        FilmCompany filmCompany = filmCompanyRepository.findById(request.getFilmCompanyId())
                .orElseThrow(() -> new RuntimeException("Кинокомпания не найдена"));

        mapper.updateEntity(movie, request, genre, country, director, filmCompany);

        Movie updated = movieRepository.save(movie);

        return mapper.toResponse(updated);
    }

    public void deleteMovie(Long id){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Фильм не найден"));

        if (movie.getPosterPath() != null) {
            fileStorageService.deletePoster(movie.getPosterPath());
        }

        movieRepository.delete(movie);
    }

    public void uploadPoster(Long id, MultipartFile file) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Фильм не найден"));

        if (movie.getPosterPath() != null) {
            fileStorageService.deletePoster(movie.getPosterPath());
        }

        String path = fileStorageService.savePoster(id, file);

        movie.setPosterPath(path);
        movieRepository.save(movie);
    }

    public Movie findEntityById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Фильм не найден"));
    }
}
