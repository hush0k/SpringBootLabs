package kz.kbtu.tsis5v2.dto.requests;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kz.kbtu.tsis5v2.enums.Gender;

import java.util.Set;

public record TeacherUpdateDto(

        @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
        String firstName,

        @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
        String lastName,

        Gender gender,

        @Size(min = 10, max = 15, message = "Phone number length must be between 10 and 15 digits")
        @Pattern(
                regexp = "^\\+?[0-9]\\d{9,14}$",
                message = "Phone number must be in correct format: +77001234567"
        )
        String phoneNumber,

        Boolean isActive,

        Set<
                @Pattern(
                        regexp = "^[A-Z0-9_-]{2,30}$",
                        message = "Subject code must be like CS104, CS-104 or CS_104 (2-30 characters)"
                )
                        String
                > subjectIds

) {}