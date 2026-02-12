package kz.kbtu.tsis3.service;

import kz.kbtu.tsis3.domain.User;
import kz.kbtu.tsis3.dto.UserCreateDto;
import kz.kbtu.tsis3.dto.UserDto;
import kz.kbtu.tsis3.mapper.UserMapper;
import kz.kbtu.tsis3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    public UserDto create(UserCreateDto userCreateDto) {
        User user = User.builder()
                .name(userCreateDto.name())
                .age(userCreateDto.age())
                .gender(userCreateDto.gender())
                .build();
        User savedUser = userRepository.save(user);
        return new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getAge(), savedUser.getGender());
    }
}
