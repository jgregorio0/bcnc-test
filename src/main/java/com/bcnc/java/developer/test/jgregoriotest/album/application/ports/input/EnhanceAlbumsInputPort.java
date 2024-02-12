package com.bcnc.java.developer.test.jgregoriotest.album.application.ports.input;

import com.bcnc.java.developer.test.jgregoriotest.album.domain.model.Album;
import com.bcnc.java.developer.test.jgregoriotest.photo.domain.model.Photo;

import java.util.Map;
import java.util.Set;

public interface EnhanceAlbumsInputPort {
    void enhanceAlbums(Map<Long, Album> albums, Set<Photo> photos);
}
