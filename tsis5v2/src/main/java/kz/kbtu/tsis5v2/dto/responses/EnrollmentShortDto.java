package kz.kbtu.tsis5v2.dto.responses;

import java.time.LocalDateTime;

public record EnrollmentShortDto(
        Long id,
        String subjectId,
        String subjectName,
        Integer semester,
        Float grade,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
