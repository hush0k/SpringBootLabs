package kz.kbtu.tsis4.dto.response;

import kz.kbtu.tsis4.enums.Theme;

import java.time.LocalDate;
import java.util.UUID;

public record EventResponseDto(
        UUID id,
        String name,
        String email,
        LocalDate dateOfStart,
        Integer duration,
        String description,
        Theme theme,
        LocalDate createdAt,
        LocalDate updatedAt
) {}
