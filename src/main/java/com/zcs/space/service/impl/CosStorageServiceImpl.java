package com.zcs.space.service.impl;

import com.zcs.space.dto.FileUploadDto;
import com.zcs.space.service.StorageService;
import org.springframework.stereotype.Service;

@Service("COS")
public class CosStorageServiceImpl implements StorageService {
    @Override
    public FileUploadDto initFileUpload() {
        return null;
    }
}
