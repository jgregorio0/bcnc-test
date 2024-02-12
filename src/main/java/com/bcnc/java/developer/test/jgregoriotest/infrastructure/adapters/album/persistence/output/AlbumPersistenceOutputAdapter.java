package com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.album.persistence.output;

import com.bcnc.java.developer.test.jgregoriotest.application.album.ports.output.AlbumPersistenceOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.domain.model.Album;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AlbumPersistenceOutputAdapter implements AlbumPersistenceOutputPort {

    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    @Override
    public Album saveAlbum(Album album) {
        return albumMapper.toAlbum(
                albumRepository.save(
                        albumMapper.toEntity(album)));
    }

    @Override
    public List<Album> getAlbums() {
        return albumRepository.findAll().stream()
                .map(albumMapper::toAlbum)
                .toList();
    }
}
