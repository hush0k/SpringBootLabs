package kz.kbtu.lecture4.service;

import kz.kbtu.lecture4.controller.dto.UserCreateDto;
import kz.kbtu.lecture4.domain.User;
import kz.kbtu.lecture4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(UserCreateDto user) {
        User u = User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();

        userRepository.save(u);
    }
}
