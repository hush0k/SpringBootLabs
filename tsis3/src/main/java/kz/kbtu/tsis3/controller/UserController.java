package kz.kbtu.tsis3.controller;

import kz.kbtu.tsis3.dto.UserCreateDto;
import kz.kbtu.tsis3.dto.UserDto;
import kz.kbtu.tsis3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("api/user/create")
    public UserDto createUser(@RequestBody UserCreateDto user) {
        return userService.create(user);
    }
}
