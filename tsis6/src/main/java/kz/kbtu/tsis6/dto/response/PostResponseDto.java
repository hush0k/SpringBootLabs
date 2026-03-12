package kz.kbtu.tsis6.dto.response;

import kz.kbtu.tsis6.enums.Status;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record PostResponseDto(
        UUID id,
        ProducerShortDto producer,
        String content,
        Status status,
        Set<String> hashtags,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
