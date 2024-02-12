package com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.album.rest.input;

import com.bcnc.java.developer.test.jgregoriotest.application.album.ports.input.EnhanceAlbumsUserCase;
import com.bcnc.java.developer.test.jgregoriotest.application.album.ports.input.GetExternalAlbumsUserCase;
import com.bcnc.java.developer.test.jgregoriotest.application.album.ports.input.GetInternalAlbumsUserCase;
import com.bcnc.java.developer.test.jgregoriotest.application.album.ports.input.SaveAlbumsUserCase;
import com.bcnc.java.developer.test.jgregoriotest.application.photo.ports.output.GetPhotosUserCase;
import com.bcnc.java.developer.test.jgregoriotest.domain.model.Album;
import com.bcnc.java.developer.test.jgregoriotest.domain.model.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AlbumRestInputAdapter {

    private final EnhanceAlbumsUserCase enrichAlbumsUserCase;
    private final SaveAlbumsUserCase createAlbumUserCase;
    private final GetExternalAlbumsUserCase getExternalAlbumsUserCase;
    private final GetInternalAlbumsUserCase getInternalAlbumsUserCase;
    private final GetPhotosUserCase getPhotosUserCase;

    @PostMapping("/albums")
    public ResponseEntity<Void> createAlbums() {
        // get albums from API
        // TODO JG async request to Album and Photo
        Map<Long, Album> albums = getExternalAlbumsUserCase.getExternalAlbums();
        // get photos from API
        Set<Photo> photos = getPhotosUserCase.getPhotos();
        // enrichment albums with photos
        enrichAlbumsUserCase.enhanceAlbums(albums, photos);
        // save albums
        createAlbumUserCase.saveAll(albums.values());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/external/albums")
    public ResponseEntity<Collection<Album>> getExternalAlbums() {
        /// get albums from API
        // TODO JG async request to Album and Photo
        Map<Long, Album> albums = getExternalAlbumsUserCase.getExternalAlbums();
        // get photos from API
        Set<Photo> photos = getPhotosUserCase.getPhotos();
        // enrichment albums with photos
        enrichAlbumsUserCase.enhanceAlbums(albums, photos);
        return ResponseEntity.ok(albums.values());
    }
    @GetMapping("/internal/albums")
    public ResponseEntity<Collection<Album>> getInternalAlbums() {
        /// get albums from DB
        Map<Long, Album> albums = getInternalAlbumsUserCase.getInternalAlbums();
        return ResponseEntity.ok(albums.values());
    }
}
