package com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.rest.output;

import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.output.GetAlbumsRestOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.domain.model.Album;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class AlbumRestOutputAdapter implements GetAlbumsRestOutputPort {

    private final AlbumRestOutputRepository albumRepository;

    @Override
    public Set<Album> getAlbums() {
        return albumRepository.getAll();
    }
}
