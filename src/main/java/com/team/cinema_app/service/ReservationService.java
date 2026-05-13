package com.team.cinema_app.service;

import com.team.cinema_app.dto.OccupiedSeatResponse;
import com.team.cinema_app.dto.ReservationRequest;
import com.team.cinema_app.dto.ReservationResponse;
import com.team.cinema_app.exception.*;
import com.team.cinema_app.mapper.ReservationMapper;
import com.team.cinema_app.model.*;
import com.team.cinema_app.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationSeatRepository reservationSeatRepository;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final SeatRepository seatRepository;
    private final ReservationMapper reservationMapper;
    private final QrCodeService qrCodeService;
    private final EmailService emailService;

    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(reservationMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ReservationResponse getReservationById(UUID id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Бронь не найдена с id " + id));

        return reservationMapper.toResponse(reservation);
    }

    public List<ReservationResponse> getAllReservationsForCurrentUser(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с почтой " + email));

        return reservationRepository.findAllByUserId(user.getId())
                .stream()
                .map(reservationMapper::toResponse)
                .collect(Collectors.toList());
    }



    @Transactional
    public ReservationResponse createReservation(ReservationRequest request, Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с почтой " + email));

        Session session = sessionRepository.findById(UUID.fromString(request.getSessionId()))
                .orElseThrow(() -> new SessionNotFoundException("Сеанс не найден с id " + request.getSessionId()));

        Reservation reservation = reservationMapper.toEntity(user, session);

        List<ReservationSeat> reservationSeats = new ArrayList<>();

        double totalPrice = 0;

        for (String seatId : request.getSeatsId()) {

            boolean alreadyReserved =
                    reservationSeatRepository.existsBySessionIdAndSeatId(
                            session.getId(),
                            UUID.fromString(seatId)
                    );

            Seat seat = seatRepository.findById(UUID.fromString(seatId))
                    .orElseThrow(() -> new SeatNotFoundException("Место не найдено с id " + seatId));

            if (alreadyReserved) {
                throw new SeatAlreadyReservedException("Место уже занято. Ряд: " + seat.getSeatRow() + ", место: " + seat.getSeatColumn());
            }

            double seatPrice = Math.round(seat.getSeatType().getPriceMultiplier() * session.getBasePrice() * session.getHall().getHallType().getPriceMultiplier() * 100.0) / 100.0;

            ReservationSeat reservationSeat =
                    ReservationSeat.builder()
                            .reservation(reservation)
                            .session(session)
                            .seat(seat)
                            .price(seatPrice)
                            .build();

            reservationSeats.add(reservationSeat);
            totalPrice += seatPrice;
        }

        reservation.setReservationSeats(reservationSeats);

        reservation.setTotalPrice(totalPrice);
        Reservation saved = reservationRepository.save(reservation);

        String seatsText = reservation.getReservationSeats()
                .stream()
                .map(rs ->
                        "Ряд " + rs.getSeat().getSeatRow() +
                                " Место " + rs.getSeat().getSeatColumn()
                )
                .collect(Collectors.joining(", "));

        String text = """
                Фильм: %s
                Сеанс: %s
                Зал: %s
                Места: %s
                """
                .formatted(
                        reservation.getSession().getMovie().getTitle(),
                        reservation.getSession().getDateTime().format(DateTimeFormatter.ofPattern("dd MMMM HH:mm")),
                        reservation.getSession().getHall().getTitle(),
                        seatsText
                );


        String qrText = reservation.getId().toString();

        byte[] qrCode =
                qrCodeService.generateQrCode(qrText);

        emailService.sendReservationEmail(
                reservation.getUser().getEmail(),
                qrCode,
                text
        );

        return reservationMapper.toResponse(saved);
    }

    @Transactional
    public void deleteReservationById(UUID id) {
        reservationRepository.deleteById(id);
    }

    @Transactional
    public void deleteReservationByIdForCurrentUser(UUID id, Authentication authentication) {

        String email = authentication.getName();

        Reservation reservation = reservationRepository
                .findById(id)
                .orElseThrow(() ->
                        new ReservationNotFoundException("Бронь не найдена с id" + id)
                );

        if (!reservation.getUser()
                .getEmail()
                .equals(email)) {

            throw new AccessDeniedException(
                    "Нельзя отменить чужую бронь"
            );
        }

        reservationRepository.delete(reservation);
    }
}
