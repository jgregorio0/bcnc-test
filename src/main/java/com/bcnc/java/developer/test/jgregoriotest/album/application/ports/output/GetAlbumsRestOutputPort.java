package com.bcnc.java.developer.test.jgregoriotest.album.application.ports.output;

import com.bcnc.java.developer.test.jgregoriotest.album.domain.model.Album;

import java.util.Set;

public interface GetAlbumsRestOutputPort {
    Set<Album> getAlbums();
}
