package com.team.cinema_app.service;

import com.team.cinema_app.dto.HallTypeRequest;
import com.team.cinema_app.dto.HallTypeResponse;
import com.team.cinema_app.exception.HallTypeNotFoundException;
import com.team.cinema_app.exception.MaxHallTypesCountException;
import com.team.cinema_app.exception.MaxHallsCountException;
import com.team.cinema_app.mapper.HallTypeMapper;
import com.team.cinema_app.model.HallType;
import com.team.cinema_app.repository.HallTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HallTypeService {

    private static final long MAX_HALL_TYPES = 3;

    private final HallTypeRepository hallTypeRepository;
    private final HallTypeMapper hallTypeMapper;

    public List<HallTypeResponse> getAllHallTypes() {
        return hallTypeRepository.findAll()
                .stream()
                .map(hallTypeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public HallTypeResponse getHallTypeById(UUID id) {
        HallType hallType = hallTypeRepository.findById(id)
                .orElseThrow(() -> new HallTypeNotFoundException("Тип зала не найден c id " + id));

        return hallTypeMapper.toResponse(hallType);
    }

    public HallTypeResponse createHallType(HallTypeRequest request) {

        if (hallTypeRepository.count() >= MAX_HALL_TYPES) {
            throw new MaxHallTypesCountException("Превышено максимальное количество типов залов (%s)".formatted(MAX_HALL_TYPES));
        }

        HallType hallType = hallTypeRepository.save(hallTypeMapper.toEntity(request));

        return hallTypeMapper.toResponse(hallType);
    }

    public HallTypeResponse updateHallTypeById(UUID id, HallTypeRequest request) {
        HallType hallType = hallTypeRepository.findById(id)
                .orElseThrow(() -> new HallTypeNotFoundException("Тип зала не найден c id " + id));

        hallType.setTitle(request.getTitle());
        hallType.setPriceMultiplier(request.getPriceMultiplier());

        HallType updated = hallTypeRepository.save(hallType);

        return hallTypeMapper.toResponse(updated);
    }

    public void deleteHallTypeById(UUID id) {
        hallTypeRepository.deleteById(id);
    }
}
