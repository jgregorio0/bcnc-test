package com.bcnc.java.developer.test.jgregoriotest.album.application.ports.input;

import com.bcnc.java.developer.test.jgregoriotest.album.domain.model.Album;

import java.util.Map;

public interface GetInternalAlbumsInputPort {
    Map<Long, Album> getInternalAlbums();
}
