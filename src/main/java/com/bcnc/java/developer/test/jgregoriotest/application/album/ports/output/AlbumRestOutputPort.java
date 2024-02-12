package com.bcnc.java.developer.test.jgregoriotest.application.album.ports.output;

import com.bcnc.java.developer.test.jgregoriotest.domain.model.Album;

import java.util.Set;

public interface AlbumRestOutputPort {
    Set<Album> getAlbums();
}
