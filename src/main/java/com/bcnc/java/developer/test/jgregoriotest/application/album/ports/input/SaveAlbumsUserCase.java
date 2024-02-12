package com.bcnc.java.developer.test.jgregoriotest.application.album.ports.input;

import com.bcnc.java.developer.test.jgregoriotest.domain.model.Album;

import java.util.Collection;

public interface SaveAlbumsUserCase {
    void saveAll(Collection<Album> albums);
}
