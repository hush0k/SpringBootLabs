package kz.kbtu.lecture3.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
public record CarDto (
        UUID id,
        String brandAndName,
        int horsePower
){}
