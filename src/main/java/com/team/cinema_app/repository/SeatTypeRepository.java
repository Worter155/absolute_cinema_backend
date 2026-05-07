package com.team.cinema_app.repository;

import com.team.cinema_app.model.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SeatTypeRepository extends JpaRepository<SeatType, UUID> {
}
