package kz.kbtu.tsis5v2.dto.requests;

import jakarta.validation.constraints.*;
import kz.kbtu.tsis5v2.enums.Gender;
import kz.kbtu.tsis5v2.enums.School;

import java.time.LocalDate;
import java.util.Set;

public record StudentUpdateDto(

        @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
        String firstName,

        @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
        String lastName,

        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth,

        Gender gender,

        @Size(min = 10, max = 15, message = "Phone number length must be between 10 and 15 digits")
        @Pattern(
                regexp = "^\\+?[0-9]\\d{9,14}$",
                message = "Phone number must be in correct format: +77001234567"
        )
        String phoneNumber,

        School school,

        @Positive(message = "Year of study must be positive")
        Integer yearOfStudy,

        @Positive(message = "GPA must be positive")
        Float gpa,

        Boolean isActive,

        Set<
                @Positive(message = "Subject id must be positive")
                        Long
                > enrolledSubjectIds
){}