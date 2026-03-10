package kz.kbtu.tsis5v2.dto.responses;

import kz.kbtu.tsis5v2.enums.School;

import java.time.LocalDateTime;
import java.util.Set;

public record SubjectResponseDto(
        String id,
        Set<EnrollmentShortDto> enrollments,
        Set<TeacherShortDto> teachers,
        String name,
        School school,
        Integer credits,
        String description,
        Set<String> prerequisites,
        Integer maxStudents,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
