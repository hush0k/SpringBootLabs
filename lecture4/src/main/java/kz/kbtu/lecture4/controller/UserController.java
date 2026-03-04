package kz.kbtu.lecture4.controller;

import kz.kbtu.lecture4.controller.dto.UserCreateDto;
import kz.kbtu.lecture4.controller.dto.UserDto;
import kz.kbtu.lecture4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody UserCreateDto user){
        userService.createUser(user);
    }
}
