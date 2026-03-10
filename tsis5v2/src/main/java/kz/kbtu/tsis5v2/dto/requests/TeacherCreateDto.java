package kz.kbtu.tsis5v2.dto.requests;

import jakarta.validation.constraints.*;
import kz.kbtu.tsis5v2.enums.Gender;

import java.util.Set;

public record TeacherCreateDto(

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be a valid format (example: teacher@kbtu.kz)")
        String email,

        @NotBlank(message = "First name is required")
        @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
        String lastName,

        @NotNull(message = "Gender must be specified")
        Gender gender,

        @NotBlank(message = "Phone number is required")
        @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
        @Pattern(
                regexp = "^\\+?[0-9]\\d{9,14}$",
                message = "Phone number must be in correct format: +77001234567"
        )
        String phoneNumber,

        @NotNull(message = "isActive must be specified")
        Boolean isActive,

        Set<
                @NotBlank(message = "Subject code cannot be blank")
                @Pattern(
                        regexp = "^[A-Z0-9_-]{2,30}$",
                        message = "Subject code must be like CS104, CS-104 or CS_104 (2-30 characters)"
                )
                        String
                > subjectIds
){}
