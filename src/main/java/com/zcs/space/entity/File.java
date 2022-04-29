package com.zcs.space.entity;

import com.zcs.space.enums.FileStatus;
import com.zcs.space.enums.FileType;
import com.zcs.space.enums.Storage;
import lombok.Data;

import javax.persistence.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class File extends AbstractEntity {
    private String name;

    private String key;

    private String ext;

    private Integer size;

    @Enumerated(EnumType.STRING)
    private FileType type;

    @Enumerated(EnumType.STRING)
    private Storage storage;

    @Enumerated(EnumType.STRING)
    private FileStatus status = FileStatus.UPLOADING;

}
