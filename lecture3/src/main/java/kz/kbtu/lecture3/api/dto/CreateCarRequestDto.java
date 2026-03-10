package kz.kbtu.lecture3.api.dto;


import java.util.UUID;


public record CreateCarRequestDto (
    UUID id,
    String brand,
    String name,
    Integer horsePower
){}


