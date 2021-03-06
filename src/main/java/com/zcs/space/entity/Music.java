package com.zcs.space.entity;

import com.zcs.space.enums.MusicStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class Music extends AbstractEntity {
    private String name;

    @Enumerated(EnumType.STRING)
    private MusicStatus status;

    private String description;
}
