package com.team.cinema_app.service;

import com.team.cinema_app.dto.SeatRequest;
import com.team.cinema_app.dto.SeatResponse;
import com.team.cinema_app.exception.HallNotFoundException;
import com.team.cinema_app.exception.SeatAlreadyExistsException;
import com.team.cinema_app.exception.SeatNotInHallException;
import com.team.cinema_app.exception.SeatTypeNotFoundException;
import com.team.cinema_app.mapper.SeatMapper;
import com.team.cinema_app.model.Hall;
import com.team.cinema_app.model.Seat;
import com.team.cinema_app.model.SeatType;
import com.team.cinema_app.repository.HallRepository;
import com.team.cinema_app.repository.SeatRepository;
import com.team.cinema_app.repository.SeatTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final HallRepository hallRepository;
    private final SeatTypeRepository seatTypeRepository;
    private final SeatMapper seatMapper;

    public List<SeatResponse> getAllSeatsByHallId(UUID hallId){
        return seatRepository.findAllByHallId(hallId)
                .stream()
                .map(seatMapper::toResponse)
                .collect(Collectors.toList());
    }

    public SeatResponse getSeatById(UUID id){
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new SeatTypeNotFoundException("Место не найдено c id " + id));

        return seatMapper.toResponse(seat);
    }

    public SeatResponse createSeat(SeatRequest request){

        if(seatRepository.existsByHallIdAndSeatRowAndSeatColumn(UUID.fromString(request.getHallId()), request.getSeatRow(), request.getSeatColumn())){
            throw new SeatAlreadyExistsException("Такое место уже существует");
        }

        Hall hall = hallRepository.findById(UUID.fromString(request.getHallId()))
                .orElseThrow(() -> new HallNotFoundException("Зал не найден c id " + request.getHallId()));

        if(request.getSeatRow() > hall.getRows()){
            throw new SeatNotInHallException("Ряд места больше чем количество рядов в зале");
        }
        if(request.getSeatColumn() > hall.getColumns()){
            throw new SeatNotInHallException("Место в ряду больше чем количество мест в ряду в зале");
        }

        SeatType seatType = seatTypeRepository.findById(UUID.fromString(request.getSeatTypeId()))
                .orElseThrow(() -> new SeatTypeNotFoundException("Тип места не найден c id " + request.getSeatTypeId()));

        Seat saved = seatRepository.save(seatMapper.toEntity(request, hall, seatType));

        return seatMapper.toResponse(saved);
    }

    public SeatResponse updateSeatById(UUID id, SeatRequest request){
        Hall hall = hallRepository.findById(UUID.fromString(request.getHallId()))
                .orElseThrow(() -> new HallNotFoundException("Зал не найден c id " + request.getHallId()));

        if(request.getSeatRow() > hall.getRows()){
            throw new SeatNotInHallException("Ряд места больше чем количество рядов в зале");
        }
        if(request.getSeatColumn() > hall.getColumns()){
            throw new SeatNotInHallException("Место в ряду больше чем количество мест в ряду в зале");
        }

        SeatType seatType = seatTypeRepository.findById(UUID.fromString(request.getSeatTypeId()))
                .orElseThrow(() -> new SeatTypeNotFoundException("Тип места не найден c id " + request.getSeatTypeId()));

        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new SeatTypeNotFoundException("Место не найдено c id " + id));

        seatMapper.updateEntity(seat,request,hall,seatType);

        return seatMapper.toResponse(seat);
    }

    public void deleteSeatById(UUID id){
        seatRepository.deleteById(id);
    }
}
