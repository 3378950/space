package com.zcs.space.controller;

import com.zcs.space.dto.UserCreateDto;
import com.zcs.space.mapper.UserMapper;
import com.zcs.space.service.UserService;
import com.zcs.space.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    UserMapper userMapper;

    @GetMapping("/")
    List<UserVo> list() {
        return userService.list().stream()
                .map(userMapper::toVo).collect(Collectors.toList());
    }

    @PostMapping("/")
    UserVo create(@RequestBody UserCreateDto userCreateDto) {
        // @RequestBody: json from fontend -> userCreateDto
        return userMapper.toVo(userService.create(userCreateDto));
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
