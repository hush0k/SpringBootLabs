package kz.kbtu.tsis3.dto;

import kz.kbtu.tsis3.domain.Gender;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
public record UserDto(
    UUID id,
    String name,
    Integer age,
    Gender gender
) {}
