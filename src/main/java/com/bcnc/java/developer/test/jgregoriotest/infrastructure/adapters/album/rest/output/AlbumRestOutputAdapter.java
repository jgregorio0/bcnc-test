package com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.album.rest.output;

import com.bcnc.java.developer.test.jgregoriotest.application.album.ports.output.AlbumRestOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.domain.model.Album;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class AlbumRestOutputAdapter implements AlbumRestOutputPort {

    private final AlbumRestOutputRepository albumRepository;

    @Override
    public Set<Album> getAlbums() {
        return albumRepository.getAll();
    }
}
