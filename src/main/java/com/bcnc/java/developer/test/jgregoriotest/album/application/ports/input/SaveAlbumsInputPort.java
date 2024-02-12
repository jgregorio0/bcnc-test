package com.bcnc.java.developer.test.jgregoriotest.album.application.ports.input;

import com.bcnc.java.developer.test.jgregoriotest.album.domain.model.Album;

import java.util.Collection;

public interface SaveAlbumsInputPort {
    void saveAll(Collection<Album> albums);
}
