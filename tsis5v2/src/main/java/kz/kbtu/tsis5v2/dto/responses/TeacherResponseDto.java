package kz.kbtu.tsis5v2.dto.responses;

import kz.kbtu.tsis5v2.enums.Gender;

import java.time.LocalDateTime;
import java.util.Set;

public record TeacherResponseDto(
        Long id,
        Set<SubjectShortDto> subjects,
        String email,
        String firstName,
        String lastName,
        Gender gender,
        String phoneNumber,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
