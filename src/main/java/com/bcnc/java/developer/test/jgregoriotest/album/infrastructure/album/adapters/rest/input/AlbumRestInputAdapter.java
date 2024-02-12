package com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.rest.input;

import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.input.EnhanceAlbumsInputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.input.GetExternalAlbumsInputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.input.GetInternalAlbumsInputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.input.SaveAlbumsInputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.domain.model.Album;
import com.bcnc.java.developer.test.jgregoriotest.photo.application.ports.input.GetPhotosInputPort;
import com.bcnc.java.developer.test.jgregoriotest.photo.domain.model.Photo;
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

    private final EnhanceAlbumsInputPort enrichAlbumsUserCase;
    private final SaveAlbumsInputPort createAlbumUserCase;
    private final GetExternalAlbumsInputPort getExternalAlbumsUserCase;
    private final GetInternalAlbumsInputPort getInternalAlbumsUserCase;
    private final GetPhotosInputPort getPhotosUserCase;

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
        return ResponseEntity.ok(albums.values());// TODO JG use pagination
    }
}
