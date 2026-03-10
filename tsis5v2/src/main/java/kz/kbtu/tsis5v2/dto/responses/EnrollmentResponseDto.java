package kz.kbtu.tsis5v2.dto.responses;

import java.time.LocalDateTime;

public record EnrollmentResponseDto(
        Long id,
        StudentShortDto student,
        SubjectShortDto subject,
        Integer semester,
        Float grade,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
