package kz.kbtu.tsis6.dto.response;

import kz.kbtu.tsis6.enums.Status;

import java.util.Set;
import java.util.UUID;

public record PostShortDto(
     UUID id,
     String content,
     Status status,
     Set<String> hashtags
) {}
