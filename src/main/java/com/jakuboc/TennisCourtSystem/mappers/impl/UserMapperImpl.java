package com.jakuboc.TennisCourtSystem.mappers.impl;

import com.jakuboc.TennisCourtSystem.domain.dto.UserDto;
import com.jakuboc.TennisCourtSystem.domain.entities.User;
import com.jakuboc.TennisCourtSystem.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements Mapper<User, UserDto> {
    private final ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto mapTo(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
