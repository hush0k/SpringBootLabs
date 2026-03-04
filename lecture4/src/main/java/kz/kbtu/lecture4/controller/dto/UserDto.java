package kz.kbtu.lecture4.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class UserDto {
    UUID id;
    String name;
    String email;
}
