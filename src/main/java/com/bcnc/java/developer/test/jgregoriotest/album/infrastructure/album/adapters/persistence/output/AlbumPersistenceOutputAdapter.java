package com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.persistence.output;

import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.output.GetAlbumsPersistenceOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.output.SaveAlbumPersistenceOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.domain.model.Album;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AlbumPersistenceOutputAdapter implements SaveAlbumPersistenceOutputPort, GetAlbumsPersistenceOutputPort {

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
