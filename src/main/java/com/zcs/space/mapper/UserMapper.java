package com.zcs.space.mapper;

import com.zcs.space.dto.UserCreateDto;
import com.zcs.space.dto.UserDto;
import com.zcs.space.entity.User;
import com.zcs.space.vo.UserVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserDto toDto(User user);

    UserVo toVo(UserDto userDto);

    User createEntity(UserCreateDto userCreateDto);
}
