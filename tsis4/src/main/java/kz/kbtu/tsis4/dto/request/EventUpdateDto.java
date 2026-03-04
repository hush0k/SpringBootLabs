package kz.kbtu.tsis4.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import kz.kbtu.tsis4.enums.Theme;

import java.time.LocalDateTime;

public record EventUpdateDto(
        @NotBlank
        @Size(min=2, max=60, message = "Length of name should be more than 2 and less than 60")
        String name,

        @NotNull
        @JsonFormat(pattern = "dd:MM:yyyy")
        @Schema(example = "03:03:2026", description = "Format: dd:MM:yyyy")
        @Future(message = "Enter correct date from tomorrow")
        LocalDateTime dateOfStart,

        @NotNull
        @Positive(message = "Enter only positive number")
        Integer duration,

        @Size(min=10, max=1000, message = "Length of description should be more than 10 and less than 1000")
        String description,

        @NotNull
        Theme theme
) {}
