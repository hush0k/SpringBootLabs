package kz.kbtu.tsis5v2.dto.requests;

import jakarta.validation.constraints.*;
import kz.kbtu.tsis5v2.enums.School;

import java.util.Set;

public record SubjectUpdateDto(
        @Pattern(
                regexp = "^[A-Z0-9_-]{2,30}$",
                message = "Prerequisite id must be like CS101 or CS_101"
        )
        String id,

        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,

        School school,

        @Positive(message = "Credits must be positive")
        Integer credits,

        @Size(min = 15, max = 500, message = "Description must be 15-500 characters")
        String description,

        @Positive(message = "Max students must be positive")
        Integer maxStudents,

        Boolean isActive,

        Set<
                @Pattern(
                        regexp = "^[A-Z0-9_-]{2,30}$",
                        message = "Prerequisite id must be like CS101 or CS_101"
                )
                        String
                > prerequisiteIds,

        Set<
                @Positive(message = "Student id must be positive")
                        Long
                > enrolledStudentIds,

        Set<
                @Positive(message = "Teacher id must be positive")
                        Long
                > teacherIds
) {
}