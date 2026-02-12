package kz.kbtu.tsis3.mapper;

import kz.kbtu.tsis3.domain.User;
import kz.kbtu.tsis3.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getAge(),
                user.getGender()
        );
    }
}
