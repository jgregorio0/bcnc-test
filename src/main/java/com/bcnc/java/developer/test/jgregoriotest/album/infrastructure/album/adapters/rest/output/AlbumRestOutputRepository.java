package com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.rest.output;

import com.bcnc.java.developer.test.jgregoriotest.album.domain.model.Album;

import java.util.Set;

public interface AlbumRestOutputRepository {

    Set<Album> getAll();

}
