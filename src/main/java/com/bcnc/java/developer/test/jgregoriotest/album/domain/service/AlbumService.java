package com.bcnc.java.developer.test.jgregoriotest.album.domain.service;

import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.input.EnhanceAlbumsInputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.input.GetExternalAlbumsInputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.input.GetInternalAlbumsInputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.output.GetAlbumsPersistenceOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.output.GetAlbumsRestOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.output.SaveAlbumPersistenceOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.domain.model.Album;
import com.bcnc.java.developer.test.jgregoriotest.photo.domain.model.Photo;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AlbumService implements EnhanceAlbumsInputPort, GetExternalAlbumsInputPort, GetInternalAlbumsInputPort {

    private final SaveAlbumPersistenceOutputPort saveAlbumPersistenceOutputPort;
    private final GetAlbumsPersistenceOutputPort getAlbumsPersistenceOutputPort;
    private final GetAlbumsRestOutputPort getAlbumsRestOutputPort;

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
        albums.stream().parallel().forEach(saveAlbumPersistenceOutputPort::saveAlbum);
    }

    @Override
    public Map<Long, Album> getExternalAlbums() {
        // get albums from API
        return transformAlbums2Map(getAlbumsRestOutputPort.getAlbums());
        // get albums from DB
    }

    @Override
    public Map<Long, Album> getInternalAlbums() {
        // get albums from DB
        return transformAlbums2Map(getAlbumsPersistenceOutputPort.getAlbums());
    }

    // TODO JG to static Utils
    private Map<Long, Album> transformAlbums2Map(Collection<Album> albums) {
        return albums.stream().parallel().collect(Collectors.toMap(Album::getId, album -> album));
    }
}
