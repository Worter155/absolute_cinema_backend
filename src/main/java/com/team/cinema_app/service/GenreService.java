package com.team.cinema_app.service;

import com.team.cinema_app.dto.GenreRequest;
import com.team.cinema_app.dto.GenreResponse;
import com.team.cinema_app.exception.GenreNotFoundException;
import com.team.cinema_app.mapper.GenreMapper;
import com.team.cinema_app.model.Genre;
import com.team.cinema_app.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public List<GenreResponse> getAllGenres() {
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::toResponse)
                .collect(Collectors.toList());
    }

    public GenreResponse getGenreById(UUID id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Жанр не найден c id " + id));
        return genreMapper.toResponse(genre);
    }

    public GenreResponse createGenre(GenreRequest request){
        Genre genre = genreMapper.toEntity(request);
        genreRepository.save(genre);

        return genreMapper.toResponse(genre);
    }

    public void deleteGenre(UUID id){
        genreRepository.deleteById(id);
    }
}
