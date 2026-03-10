package kz.kbtu.tsis5v2.dto.requests;

import jakarta.validation.constraints.*;
import kz.kbtu.tsis5v2.enums.Gender;
import kz.kbtu.tsis5v2.enums.School;

import java.time.LocalDate;
import java.util.Set;

public record StudentCreateDto(

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid (example: student@kbtu.kz)")
        String email,

        @NotBlank(message = "First name is required")
        @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
        String lastName,

        @NotNull(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth,

        @NotNull(message = "Gender must be specified")
        Gender gender,

        @NotBlank(message = "Phone number is required")
        @Size(min = 10, max = 15, message = "Phone number length must be between 10 and 15 digits")
        @Pattern(
                regexp = "^\\+?[0-9]\\d{9,14}$",
                message = "Phone number must be in correct format: +77001234567"
        )
        String phoneNumber,

        @NotNull(message = "School must be specified")
        School school,

        @NotNull(message = "Year of study is required")
        @Positive(message = "Year of study must be positive")
        Integer yearOfStudy,

        @NotNull(message = "GPA is required")
        @Positive(message = "GPA must be positive")
        Float gpa,

        @NotNull(message = "isActive must be specified")
        Boolean isActive,

        Set<
                @Positive(message = "Subject id must be positive")
                        Long
                > enrolledSubjectIds
){}
