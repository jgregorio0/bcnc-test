package com.bcnc.java.developer.test.jgregoriotest.application.album.ports.output;

import com.bcnc.java.developer.test.jgregoriotest.domain.model.Album;

import java.util.List;

public interface AlbumPersistenceOutputPort {
    Album saveAlbum(Album album);

    List<Album> getAlbums();
}
