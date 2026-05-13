package com.team.cinema_app.service;

import com.team.cinema_app.dto.FilmCompanyRequest;
import com.team.cinema_app.dto.FilmCompanyResponse;
import com.team.cinema_app.exception.FilmCompanyNotFoundException;
import com.team.cinema_app.exception.MaxFilmCompaniesCountException;
import com.team.cinema_app.exception.MaxHallsCountException;
import com.team.cinema_app.mapper.FilmCompanyMapper;
import com.team.cinema_app.model.FilmCompany;
import com.team.cinema_app.repository.FilmCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmCompanyService {

    private static final long MAX_FILM_COMPANIES = 60;

    private final FilmCompanyRepository filmCompanyRepository;
    private final FilmCompanyMapper filmCompanyMapper;

    public List<FilmCompanyResponse> getAllFilmCompanies() {
        return filmCompanyRepository.findAll()
                .stream()
                .map(filmCompanyMapper::toResponse)
                .collect(Collectors.toList());
    }

    public FilmCompanyResponse getFilmCompanyById(UUID id) {
        FilmCompany filmCompany = filmCompanyRepository.findById(id)
                .orElseThrow(() -> new FilmCompanyNotFoundException("Жанр не найден c id " + id));
        return filmCompanyMapper.toResponse(filmCompany);
    }

    public FilmCompanyResponse createFilmCompany(FilmCompanyRequest request) {

        if (filmCompanyRepository.count() >= MAX_FILM_COMPANIES) {
            throw new MaxFilmCompaniesCountException("Превышено максимальное количество кинокомпаний (%s)".formatted(MAX_FILM_COMPANIES));
        }

        FilmCompany filmCompany = filmCompanyRepository.save(filmCompanyMapper.toEntity(request));

        return filmCompanyMapper.toResponse(filmCompany);
    }

    public FilmCompanyResponse updateFilmCompanyById(UUID id, FilmCompanyRequest request) {
        FilmCompany filmCompany = filmCompanyRepository.findById(id)
                .orElseThrow(() -> new FilmCompanyNotFoundException("Жанр не найден с id " + id));

        filmCompany.setTitle(request.getTitle());

        FilmCompany updated = filmCompanyRepository.save(filmCompany);

        return filmCompanyMapper.toResponse(updated);
    }

    public void deleteFilmCompany(UUID id) {
        filmCompanyRepository.deleteById(id);
    }
}
