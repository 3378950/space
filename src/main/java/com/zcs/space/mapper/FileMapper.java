package com.zcs.space.mapper;

import com.zcs.space.dto.FileUploadRequest;
import com.zcs.space.entity.File;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {
    File createEntity(FileUploadRequest fileUploadRequest);
}