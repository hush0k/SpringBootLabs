package kz.kbtu.tsis5v2.dto.responses;

import kz.kbtu.tsis5v2.enums.Gender;
import kz.kbtu.tsis5v2.enums.School;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record StudentResponseDto(
        Long id,
        Set<EnrollmentShortDto> enrollments,
        String email,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        Gender gender,
        String phoneNumber,
        School school,
        Integer yearOfStudy,
        Float gpa,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
