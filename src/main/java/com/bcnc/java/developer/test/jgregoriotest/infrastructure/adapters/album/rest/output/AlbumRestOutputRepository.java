package com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.album.rest.output;

import com.bcnc.java.developer.test.jgregoriotest.domain.model.Album;

import java.util.Set;

public interface AlbumRestOutputRepository {

    Set<Album> getAll();

}
