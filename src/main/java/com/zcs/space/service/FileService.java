package com.zcs.space.service;


import com.zcs.space.dto.FileUploadDto;
import com.zcs.space.dto.FileUploadRequest;

public interface FileService {

    FileUploadDto initUpload(FileUploadRequest fileUploadRequest);

}
