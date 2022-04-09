package com.zcs.space.service;

import com.zcs.space.dto.UserDto;
import com.zcs.space.mapper.UserMapper;
import com.zcs.space.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository repository;

    UserMapper mapper;

    @Override
    public List<UserDto> list() {
        return repository.findAll().
                stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }
}
