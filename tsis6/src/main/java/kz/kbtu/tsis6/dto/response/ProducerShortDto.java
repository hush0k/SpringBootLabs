package kz.kbtu.tsis6.dto.response;

import java.util.UUID;

public record ProducerShortDto(
        UUID id,
        String firstName,
        String lastName
) {}
