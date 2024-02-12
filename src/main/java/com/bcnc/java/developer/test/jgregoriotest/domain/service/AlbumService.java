package com.bcnc.java.developer.test.jgregoriotest.domain.service;

import com.bcnc.java.developer.test.jgregoriotest.application.album.ports.input.EnhanceAlbumsUserCase;
import com.bcnc.java.developer.test.jgregoriotest.application.album.ports.input.GetExternalAlbumsUserCase;
import com.bcnc.java.developer.test.jgregoriotest.application.album.ports.input.GetInternalAlbumsUserCase;
import com.bcnc.java.developer.test.jgregoriotest.application.album.ports.input.SaveAlbumsUserCase;
import com.bcnc.java.developer.test.jgregoriotest.application.album.ports.output.AlbumPersistenceOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.application.album.ports.output.AlbumRestOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.application.photo.ports.output.PhotoRestOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.domain.model.Album;
import com.bcnc.java.developer.test.jgregoriotest.domain.model.Photo;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AlbumService implements EnhanceAlbumsUserCase, SaveAlbumsUserCase, GetExternalAlbumsUserCase, GetInternalAlbumsUserCase {

    private final AlbumPersistenceOutputPort albumPersistenceOutputPort;
    private final AlbumRestOutputPort albumRestOutputPort;

    @Override
    public void enhanceAlbums(Map<Long, Album> albums, Set<Photo> photos) {
        if (photos == null || albums == null) {
            throw new IllegalArgumentException("Albums or photos cannot be null");
        }
        photos.stream().parallel().forEach(photo -> {
            Album album = albums.get(photo.getAlbumId());
            if (album != null) {
                album.getPhotos().add(photo);
            }
        });
    }

    @Override
    public void saveAll(Collection<Album> albums) {
        albums.stream().parallel().forEach(albumPersistenceOutputPort::saveAlbum);
    }

    @Override
    public Map<Long, Album> getExternalAlbums() {
        // get albums from API
        return transformAlbums2Map(albumRestOutputPort.getAlbums());
        // get albums from DB
    }

    @Override
    public Map<Long, Album> getInternalAlbums() {
        // get albums from DB
        return transformAlbums2Map(albumPersistenceOutputPort.getAlbums());
    }

    // TODO JG to static Utils
    private Map<Long, Album> transformAlbums2Map(Collection<Album> albums) {
        return albums.stream().parallel().collect(Collectors.toMap(Album::getId, album -> album));
    }
}
