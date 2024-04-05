package com.jakuboc.TennisCourtSystem.Services;

import com.jakuboc.TennisCourtSystem.domain.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
}
