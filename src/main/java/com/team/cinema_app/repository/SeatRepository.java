package com.team.cinema_app.repository;

import com.team.cinema_app.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SeatRepository extends JpaRepository<Seat, UUID> {
    List<Seat> findAllByHallId(UUID hallId);
    boolean existsByHallIdAndSeatRowAndSeatColumn(UUID hallId, int seatRow, int seatColumn);
}
