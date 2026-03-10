package kz.kbtu.tsis5v2.dto.responses;

public record StudentShortDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        Integer yearOfStudy
) {
}
