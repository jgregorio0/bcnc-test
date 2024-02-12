package com.bcnc.java.developer.test.jgregoriotest.album.application.ports.output;

import com.bcnc.java.developer.test.jgregoriotest.album.domain.model.Album;

public interface SaveAlbumPersistenceOutputPort {
    Album saveAlbum(Album album);
}
