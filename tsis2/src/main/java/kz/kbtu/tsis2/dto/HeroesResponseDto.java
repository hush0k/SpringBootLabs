package kz.kbtu.tsis2.dto;

import java.util.List;

public record HeroesResponseDto(
        Integer serverTotalHeroesLength,
        List<HeroDto> heroes
) {}
