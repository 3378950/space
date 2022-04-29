package com.zcs.space.mapper;

import com.zcs.space.dto.FileUploadDto;
import com.zcs.space.vo.FileUploadVo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileUploadMapper {
    FileUploadVo toVo(FileUploadDto fileUploadDto);
}
