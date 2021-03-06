package com.zcs.space.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zcs.space.config.SecurityConfig;
import com.zcs.space.dto.TokenCreateRequest;
import com.zcs.space.dto.UserCreateRequest;
import com.zcs.space.dto.UserDto;
import com.zcs.space.dto.UserUpdateRequest;
import com.zcs.space.entity.User;
import com.zcs.space.exception.BizException;
import com.zcs.space.exception.ExceptionType;
import com.zcs.space.mapper.UserMapper;
import com.zcs.space.repository.UserRepository;
import com.zcs.space.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository repository;

    UserMapper mapper;

    PasswordEncoder passwordEncoder;


    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {
        // check username exist
        checkUserName(userCreateRequest.getUsername());
        // create User Entity
        User user = mapper.createEntity(userCreateRequest);
        // password encode
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //
        return mapper.toDto(repository.save(user));
    }


    @Override
    public UserDto get(String id) {
        Optional<User> user = repository.findById(id);

        if(user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return mapper.toDto(user.get());
    }

    @Override
    public UserDto update(String id, UserUpdateRequest userUpdateRequest) {
        Optional<User> user = repository.findById(id);

        if(user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }

        return mapper.toDto(repository.save(mapper.updateEntity(user.get(), userUpdateRequest)));
    }

    @Override
    public void delete(String id) {

        Optional<User> user = repository.findById(id);

        if(!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }

        repository.delete(user.get());
    }

    @Override
    public Page<UserDto> search(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if(!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user.get();
    }

    // ?????????????????????
    private void checkUserName(String username) {
        Optional<User> user = repository.findByUsername(username);
        if(user.isPresent()) {
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
    }

    @Override
    public String createToken(TokenCreateRequest tokenCreateRequest) {
        User user = loadUserByUsername(tokenCreateRequest.getUsername());

        if(!passwordEncoder.matches(tokenCreateRequest.getPassword(), user.getPassword())) {
            throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }

        if(!user.isEnabled()) {
            throw new BizException(ExceptionType.USER_NOT_ENABLED);
        }

        if (!user.isAccountNonLocked()) {
            throw new BizException(ExceptionType.USER_LOCKED);
        }

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME)))
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = loadUserByUsername(authentication.getName());
        return mapper.toDto(currentUser);
    }

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
