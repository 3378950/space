package com.zcs.space.mapper;

import com.zcs.space.dto.MusicCreateRequest;
import com.zcs.space.dto.MusicDto;
import com.zcs.space.dto.MusicUpdateRequest;
import com.zcs.space.entity.Music;
import com.zcs.space.vo.MusicVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MusicMapper {
    MusicDto toDto(Music music);

    MusicVo toVo(MusicDto musicDto);

    Music createEntity(MusicCreateRequest musicCreateRequest);

    Music updateEntity(@MappingTarget Music music, MusicUpdateRequest musicUpdateRequest);
}