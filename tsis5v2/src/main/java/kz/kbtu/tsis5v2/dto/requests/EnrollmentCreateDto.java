package kz.kbtu.tsis5v2.dto.requests;

import jakarta.validation.constraints.*;

public record EnrollmentCreateDto(

        @NotNull(message = "Student id is required")
        @Positive(message = "Student id must be positive")
        Long studentId,

        @NotNull(message = "Subject id is required")
        @Pattern(
                regexp = "^[A-Z0-9_-]{2,30}$",
                message = "Subject code must be like CS104, CS-104 or CS_104"
        )
        String subjectId,

        @NotNull(message = "Semester is required")
        @Min(value = 1, message = "Semester must be at least 1")
        @Max(value = 12, message = "Semester cannot be greater than 12")
        Integer semester,

        @DecimalMin(value = "0.0", message = "Grade must be at least 0.0")
        @DecimalMax(value = "4.0", message = "Grade cannot be greater than 4.0")
        Float grade
) {}
