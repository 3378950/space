package com.zcs.space.service.impl;

import com.zcs.space.dto.FileUploadDto;
import com.zcs.space.dto.FileUploadRequest;
import com.zcs.space.entity.File;
import com.zcs.space.enums.Storage;
import com.zcs.space.mapper.FileMapper;
import com.zcs.space.repository.FileRepository;
import com.zcs.space.service.FileService;
import com.zcs.space.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    private Map<String, StorageService> storageServices;

    private FileRepository repository;

    private FileMapper mapper;


    @Override
    @Transactional
    public FileUploadDto initUpload(FileUploadRequest fileUploadRequest) {
        File file = repository.save(mapper.createEntity(fileUploadRequest));
        // 通过接口获取STS令牌
        FileUploadDto fileUploadDto = storageServices.get(getDefaultStorage().name()).initFileUpload();

        fileUploadDto.setKey(file.getKey());
        return fileUploadDto;
    }

    // Todo: 后台设置当前Storage
    private Storage getDefaultStorage() {
        return Storage.COS;
    }


    @Autowired
    public void setStorageServices(Map<String, StorageService> storageServices) {
        this.storageServices = storageServices;
    }

    @Autowired
    public void setRepository(FileRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(FileMapper mapper) {
        this.mapper = mapper;
    }
}
