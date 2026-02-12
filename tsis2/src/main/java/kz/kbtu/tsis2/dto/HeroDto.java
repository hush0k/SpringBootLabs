package kz.kbtu.tsis2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record HeroDto(
        @JsonProperty("_id") String id,
        String code,
        String name,

        List<IdNameDto> roleIds,
        List<IdNameDto> specialtyIds,

        String lane,
        @JsonProperty("release_year") String releaseYear,
        @JsonProperty("battle_points") String battlePoints,
        String ticket,
        @JsonProperty("lucky_gem") String luckyGem,

        List<SkillDto> skills,
        @JsonProperty("base_attributes") List<BaseAttributesDto> baseAttributes
) {}
