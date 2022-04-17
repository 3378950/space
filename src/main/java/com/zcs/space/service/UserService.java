package com.zcs.space.service;

import com.zcs.space.dto.TokenCreateRequest;
import com.zcs.space.dto.UserCreateRequest;
import com.zcs.space.dto.UserDto;
import com.zcs.space.dto.UserUpdateRequest;
import com.zcs.space.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    UserDto create(UserCreateRequest userCreateRequest);

    @Override
    User loadUserByUsername(String username);

    UserDto get(String id);

    UserDto update(String id, UserUpdateRequest userUpdateRequest);

    void delete(String id);

    Page<UserDto> search(Pageable pageable);

    String createToken(TokenCreateRequest tokenCreateRequest);

    UserDto getCurrentUser();
}
