package kz.kbtu.tsis6.dto.response;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record ProducerResponseDto(
        UUID id,
        Set<PostShortDto> posts,
        String firstName,
        String lastName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
