package kz.kbtu.lecture4.domain.mapper;

import kz.kbtu.lecture4.controller.dto.UserDto;
import kz.kbtu.lecture4.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = java.util.UUID.class)
public interface UserMapper {
    public UserDto toUserDto(User user);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    public User toUser(UserDto userDto);
}
