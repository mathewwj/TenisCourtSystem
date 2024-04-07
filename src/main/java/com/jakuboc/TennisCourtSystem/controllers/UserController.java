package com.jakuboc.TennisCourtSystem.controllers;

import com.jakuboc.TennisCourtSystem.Services.UserService;
import com.jakuboc.TennisCourtSystem.domain.dto.UserDto;
import com.jakuboc.TennisCourtSystem.domain.entities.User;
import com.jakuboc.TennisCourtSystem.mappers.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * api controller for {@link User}
 */
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final Mapper<User, UserDto> userMapper;

    public UserController(UserService userService, Mapper<User, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(path = "/users")
    public List<UserDto> listUsers() {
        List<User> users = userService.findAll();

        return users.stream()
                .map(userMapper::mapTo)
                .collect(Collectors.toList());
    }
}
