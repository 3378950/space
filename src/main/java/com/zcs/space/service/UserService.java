package com.zcs.space.service;

import com.zcs.space.dto.UserCreateDto;
import com.zcs.space.dto.UserDto;
import com.zcs.space.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {


    List<UserDto> list();

    UserDto create(UserCreateDto userCreateDto);

    @Override
    User loadUserByUsername(String username);
}
