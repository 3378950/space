package com.zcs.space.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zcs.space.vo.MusicVo;
import lombok.Data;

import java.util.Date;

@Data
public class MusicDto {

    private String id;

    private String name;

    private MusicVo status;

    private String description;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date createdTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private Date updatedTime;
}
