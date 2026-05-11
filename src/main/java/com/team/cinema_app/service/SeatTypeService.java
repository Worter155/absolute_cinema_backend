package com.team.cinema_app.service;

import com.team.cinema_app.dto.SeatTypeRequest;
import com.team.cinema_app.dto.SeatTypeResponse;
import com.team.cinema_app.exception.SeatTypeNotFoundException;
import com.team.cinema_app.mapper.SeatTypeMapper;
import com.team.cinema_app.model.SeatType;
import com.team.cinema_app.repository.SeatTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatTypeService {

    private final SeatTypeRepository seatTypeRepository;
    private final SeatTypeMapper seatTypeMapper;

    public List<SeatTypeResponse> getAllSeatTypes(){
        return seatTypeRepository.findAll()
                .stream()
                .map(seatTypeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public SeatTypeResponse getSeatTypeById(UUID id){
        SeatType seatType = seatTypeRepository.findById(id)
                .orElseThrow(()->new SeatTypeNotFoundException("Тип места не найден c id " + id));

        return seatTypeMapper.toResponse(seatType);
    }

    public SeatTypeResponse createSeatType(SeatTypeRequest request){
        SeatType seatType = seatTypeRepository.save(seatTypeMapper.toEntity(request));

        return  seatTypeMapper.toResponse(seatType);
    }

    public SeatTypeResponse updateSeatTypeById(UUID id, SeatTypeRequest request){
        SeatType seatType = seatTypeRepository.findById(id)
                .orElseThrow(()->new SeatTypeNotFoundException("Тип места не найден c id " + id));

        seatType.setTitle(request.getTitle());
        seatType.setPriceMultiplier(request.getPriceMultiplier());

        SeatType updated = seatTypeRepository.save(seatType);

        return seatTypeMapper.toResponse(updated);
    }

    public void deleteSeatTypeById(UUID id){
        seatTypeRepository.deleteById(id);
    }
}
