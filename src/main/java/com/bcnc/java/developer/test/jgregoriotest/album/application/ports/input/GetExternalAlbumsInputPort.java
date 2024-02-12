package com.bcnc.java.developer.test.jgregoriotest.album.application.ports.input;

import com.bcnc.java.developer.test.jgregoriotest.album.domain.model.Album;

import java.util.Map;

public interface GetExternalAlbumsInputPort {
    Map<Long, Album> getExternalAlbums();
}
