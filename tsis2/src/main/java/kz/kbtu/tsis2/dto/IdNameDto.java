package kz.kbtu.tsis2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IdNameDto(
        @JsonProperty("_id") String id,
        String name
) {}