package com.jakuboc.TennisCourtSystem.controllers;

import com.jakuboc.TennisCourtSystem.Services.UserService;
import com.jakuboc.TennisCourtSystem.domain.dto.UserDto;
import com.jakuboc.TennisCourtSystem.domain.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/api/users")
    public List<UserDto> listUsers() {
        List<User> users = userService.findAll();

        return users.stream()
                .map(user -> UserDto.builder()
                        .phoneNumber(user.getPhoneNumber())
                        .name(user.getName())
                        .surname(user.getSurname())
                        .build())
                .collect(Collectors.toList());
    }
}
