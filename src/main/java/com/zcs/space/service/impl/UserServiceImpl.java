package com.zcs.space.service.impl;

import com.zcs.space.dto.UserCreateDto;
import com.zcs.space.dto.UserDto;
import com.zcs.space.entity.User;
import com.zcs.space.exception.BizException;
import com.zcs.space.exception.ExceptionType;
import com.zcs.space.mapper.UserMapper;
import com.zcs.space.repository.UserRepository;
import com.zcs.space.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository repository;

    UserMapper mapper;

    PasswordEncoder passwordEncoder;


    @Override
    public List<UserDto> list() {
        return repository.findAll().
                stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        checkUserName(userCreateDto.getUsername());
        User user = mapper.createEntity(userCreateDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.toDto(repository.save(user));
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if(!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user.get();
    }

    private void checkUserName(String username) {
        Optional<User> user = repository.findByUsername(username);
        if(user.isPresent()) {
            throw  new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
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
