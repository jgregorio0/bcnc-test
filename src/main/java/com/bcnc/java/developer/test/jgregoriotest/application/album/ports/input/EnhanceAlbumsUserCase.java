package com.bcnc.java.developer.test.jgregoriotest.application.album.ports.input;

import com.bcnc.java.developer.test.jgregoriotest.domain.model.Album;
import com.bcnc.java.developer.test.jgregoriotest.domain.model.Photo;

import java.util.Map;
import java.util.Set;

public interface EnhanceAlbumsUserCase {
    void enhanceAlbums(Map<Long, Album> albums, Set<Photo> photos);
}
