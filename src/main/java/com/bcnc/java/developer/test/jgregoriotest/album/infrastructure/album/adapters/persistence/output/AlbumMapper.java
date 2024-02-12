package com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.persistence.output;

import com.bcnc.java.developer.test.jgregoriotest.album.domain.model.Album;
import org.mapstruct.Mapper;

@Mapper
public interface AlbumMapper {
    Album toAlbum(AlbumEntity entity);

    AlbumEntity toEntity(Album album);
}