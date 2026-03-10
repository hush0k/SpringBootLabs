package kz.kbtu.tsis5v2.dto.responses;

import kz.kbtu.tsis5v2.enums.School;

public record SubjectShortDto(
        String id,
        String name,
        School school,
        Integer credits
) {
}
