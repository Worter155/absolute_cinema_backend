package com.team.cinema_app.repository;

import com.team.cinema_app.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
