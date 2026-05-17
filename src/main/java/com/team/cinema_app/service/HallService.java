package com.team.cinema_app.service;

import com.team.cinema_app.dto.HallRequest;
import com.team.cinema_app.dto.HallResponse;
import com.team.cinema_app.exception.HallHasSessionException;
import com.team.cinema_app.exception.HallNotFoundException;
import com.team.cinema_app.exception.HallTypeNotFoundException;
import com.team.cinema_app.exception.MaxHallsCountException;
import com.team.cinema_app.mapper.HallMapper;
import com.team.cinema_app.model.Hall;
import com.team.cinema_app.model.HallType;
import com.team.cinema_app.repository.HallRepository;
import com.team.cinema_app.repository.HallTypeRepository;
import com.team.cinema_app.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HallService {

    private static final long MAX_HALLS = 5;

    private final HallRepository hallRepository;
    private final HallTypeRepository hallTypeRepository;
    private final SessionRepository sessionRepository;
    private final HallMapper hallMapper;

    public List<HallResponse> getAllHalls() {
        return hallRepository.findAll()
                .stream()
                .map(hallMapper::toResponse)
                .collect(Collectors.toList());
    }

    public HallResponse getHallById(UUID id) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new HallNotFoundException("Зал не найден c id " + id));

        return hallMapper.toResponse(hall);
    }

    public HallResponse createHall(HallRequest request) {

        if (hallRepository.count() >= MAX_HALLS) {
            throw new MaxHallsCountException("Превышено максимальное количество залов (%s)".formatted(MAX_HALLS));
        }

        HallType hallType = hallTypeRepository.findById(UUID.fromString(request.getHallTypeId()))
                .orElseThrow(() -> new HallTypeNotFoundException("Тип зала не найден c id " + request.getHallTypeId()));

        Hall hall = hallRepository.save(hallMapper.toEntity(request, hallType));

        return hallMapper.toResponse(hall);
    }

    public HallResponse updateHall(UUID id, HallRequest request) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new HallNotFoundException("Зал не найден c id " + id));

        HallType hallType = hallTypeRepository.findById(UUID.fromString(request.getHallTypeId()))
                .orElseThrow(() -> new HallTypeNotFoundException("Тип зала не найден c id " + request.getHallTypeId()));


        hallMapper.updateEntity(hall, request, hallType);

        Hall updated = hallRepository.save(hall);

        return hallMapper.toResponse(updated);
    }

    public void deleteHallById(UUID id) {

        if (sessionRepository.existsByHallId(id)) {
            throw new HallHasSessionException("Зал используется в сеансе");
        }

        hallRepository.deleteById(id);
    }
}
