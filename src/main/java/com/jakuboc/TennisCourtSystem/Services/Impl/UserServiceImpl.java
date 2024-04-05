package com.jakuboc.TennisCourtSystem.Services.Impl;

import com.jakuboc.TennisCourtSystem.Services.UserService;
import com.jakuboc.TennisCourtSystem.domain.entities.User;
import com.jakuboc.TennisCourtSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
