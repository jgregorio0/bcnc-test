package com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.album.persistence.output;

import com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.persistence.output.AlbumEntity;
import com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.persistence.output.AlbumRepository;
import com.bcnc.java.developer.test.jgregoriotest.photo.infrastructure.adapters.output.persistence.PhotoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Test
    void givenAlbumWithoutPhotos_whenSave_thenAlbumIsPersisted() {
        // GIVEN
        AlbumEntity albumWithoutPhotos = AlbumEntity.builder()
                .id(1L)
                .title("Album without photos")
                .userId(1L).build();
        // WHEN
        AlbumEntity saved = albumRepository.save(albumWithoutPhotos);
        // THEN
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void givenAlbumWithPhotos_whenSave_thenAlbumAndPhotosArePersisted() {
        // GIVEN
        PhotoEntity photo1 = PhotoEntity.builder()
                .id(1L)
                .albumId(1L)
                .title("Photo 1 in Album 1")
                .url("https://via.placeholder.com/600/771796")
                .thumbnailUr("https://via.placeholder.com/150/771796")
                .build();
        PhotoEntity photo2 = PhotoEntity.builder()
                .id(2L)
                .albumId(1L)
                .title("Photo 2 in Album 1")
                .url("https://via.placeholder.com/600/771796")
                .thumbnailUr("https://via.placeholder.com/150/771796")
                .build();
        List<PhotoEntity> photos = Stream.of(photo1, photo2).toList();
        AlbumEntity album = AlbumEntity.builder()
                .id(1L)
                .title("Album with photos")
                .userId(1L)
                .photos(photos)
                .build();
        // WHEN
        AlbumEntity saved = albumRepository.save(album);
        // THEN
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getPhotos()).hasSize(2);
        assertThat(saved.getPhotos())
                .anyMatch(p -> p.getId() == 1L)
                .anyMatch(p -> p.getId() == 2L);
        assertThat(saved.getPhotos().get(0).getId()).isNotNull();
        assertThat(saved.getPhotos().get(1).getId()).isNotNull();
    }
}