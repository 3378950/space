package com.zcs.space.service;

import com.zcs.space.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<UserDto> list();

}
