package com.zcs.space.service;

import com.zcs.space.dto.MusicCreateRequest;
import com.zcs.space.dto.MusicDto;
import com.zcs.space.dto.MusicUpdateRequest;

import java.util.List;

public interface MusicService {
    MusicDto create(MusicCreateRequest musicCreateRequest);

    MusicDto update(String id, MusicUpdateRequest musicUpdateRequest);

    List<MusicDto> list();

    void publish(String id);

    void close(String id);
}
