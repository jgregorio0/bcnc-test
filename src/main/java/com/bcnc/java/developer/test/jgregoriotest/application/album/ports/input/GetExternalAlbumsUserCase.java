package com.bcnc.java.developer.test.jgregoriotest.application.album.ports.input;

import com.bcnc.java.developer.test.jgregoriotest.domain.model.Album;

import java.util.Map;

public interface GetExternalAlbumsUserCase {
    Map<Long, Album> getExternalAlbums();
}
