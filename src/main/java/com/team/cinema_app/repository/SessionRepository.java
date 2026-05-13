package com.team.cinema_app.repository;

import com.team.cinema_app.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {
    List<Session> findAllByDateTimeBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    long countByDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
