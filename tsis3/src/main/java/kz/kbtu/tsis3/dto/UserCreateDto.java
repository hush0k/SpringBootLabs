package kz.kbtu.tsis3.dto;

import kz.kbtu.tsis3.domain.Gender;

public record UserCreateDto(
        String name,
        Integer age,
        Gender gender
) {}
