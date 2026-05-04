package com.team.cinema_app.repository;

import com.team.cinema_app.model.FilmCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilmCompanyRepository extends JpaRepository<FilmCompany, UUID> {
}
