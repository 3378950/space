package com.zcs.space.mapper;

import com.zcs.space.dto.UserCreateRequest;
import com.zcs.space.dto.UserDto;
import com.zcs.space.dto.UserUpdateRequest;
import com.zcs.space.entity.User;
import com.zcs.space.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    UserVo toVo(UserDto userDto);

    User createEntity(UserCreateRequest userCreateRequest);

    User updateEntity(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
