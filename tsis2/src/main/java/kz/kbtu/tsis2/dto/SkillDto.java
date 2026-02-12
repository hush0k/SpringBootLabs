package kz.kbtu.tsis2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SkillDto(
        @JsonProperty("skill_name") String skillName,
        @JsonProperty("skill_icon") String skillIcon,
        String type,
        String cooldown,
        String manacost,
        String description
) {}
