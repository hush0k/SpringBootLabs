package kz.kbtu.tsis6.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kz.kbtu.tsis6.enums.Status;

import java.util.Set;
import java.util.UUID;

public record PostCreateDto(

        @NotNull(message = "Producer id is required")
        UUID producerId,

        @NotBlank(message = "Content must not be blank")
        @Size(min = 15, max = 1500, message = "Content must be between 15 and 1500 characters")
        String content,

        @NotNull(message = "Status is required")
        Status status,

        @Size(max = 30, message = "Post cannot contain more than 30 hashtags")
        Set<
                @Size(min = 2, max = 50, message = "Hashtag length must be between 2 and 50 characters")
                        String
                > hashtags

) {}