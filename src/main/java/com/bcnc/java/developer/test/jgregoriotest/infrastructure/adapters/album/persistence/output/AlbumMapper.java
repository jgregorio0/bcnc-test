package com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.album.persistence.output;

import com.bcnc.java.developer.test.jgregoriotest.domain.model.Album;
import org.mapstruct.Mapper;

@Mapper
public interface AlbumMapper {
    Album toAlbum(AlbumEntity entity);

    AlbumEntity toEntity(Album album);
}