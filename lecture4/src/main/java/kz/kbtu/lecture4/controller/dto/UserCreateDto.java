package kz.kbtu.lecture4.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserCreateDto {
    @NotBlank(message = "Name must not ne empty")
    @Size(min = 2, max =50, message = "Name can not be less than 2 and more than 50")
    String name;

    @Email
    @NotBlank(message = "Email can not be empty")
    String email;
}
