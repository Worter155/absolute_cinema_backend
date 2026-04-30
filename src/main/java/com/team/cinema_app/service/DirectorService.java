package com.team.cinema_app.service;

import com.team.cinema_app.dto.DirectorRequest;
import com.team.cinema_app.dto.DirectorResponse;
import com.team.cinema_app.exception.DirectorNotFoundException;
import com.team.cinema_app.mapper.DirectorMapper;
import com.team.cinema_app.model.Director;
import com.team.cinema_app.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public List<DirectorResponse> getAllDirectors() {
        return directorRepository.findAll()
                .stream()
                .map(directorMapper::toResponse)
                .collect(Collectors.toList());
    }

    public DirectorResponse getDirectorById(UUID id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("Жанр не найден c id " + id));
        return directorMapper.toResponse(director);
    }

    public DirectorResponse createDirector(DirectorRequest request) {
        Director director = directorMapper.toEntity(request);
        directorRepository.save(director);

        return directorMapper.toResponse(director);
    }

    public DirectorResponse updateDirectorById(UUID id, DirectorRequest request) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("Жанр не найден с id " + id));

        director.setName(request.getName());

        Director updated = directorRepository.save(director);

        return directorMapper.toResponse(updated);
    }

    public void deleteDirector(UUID id) {
        directorRepository.deleteById(id);
    }
}
