package com.team.cinema_app.repository;

import com.team.cinema_app.model.ReservationSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationSeatRepository extends JpaRepository <ReservationSeat, UUID>{

    boolean existsBySessionIdAndSeatId(
            UUID sessionId,
            UUID seatId
    );

    @Query("""
        SELECT rs
        FROM ReservationSeat rs
        JOIN FETCH rs.seat
        WHERE rs.session.id = :sessionId
        """)
    List<ReservationSeat> findAllBySessionId(UUID sessionId);
}
