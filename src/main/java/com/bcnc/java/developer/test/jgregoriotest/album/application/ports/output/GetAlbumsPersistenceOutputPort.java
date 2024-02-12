package com.bcnc.java.developer.test.jgregoriotest.album.application.ports.output;

import com.bcnc.java.developer.test.jgregoriotest.album.domain.model.Album;

import java.util.List;

public interface GetAlbumsPersistenceOutputPort {
    List<Album> getAlbums();
}
