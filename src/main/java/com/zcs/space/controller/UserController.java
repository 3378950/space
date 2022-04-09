package com.zcs.space.controller;

import com.zcs.space.mapper.UserMapper;
import com.zcs.space.service.UserService;
import com.zcs.space.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .map(userMapper::tovo).collect(Collectors.toList());
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
