package com.bcnc.java.developer.test.jgregoriotest.common.infrastructure.config;

import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.output.GetAlbumsPersistenceOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.output.GetAlbumsRestOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.application.ports.output.SaveAlbumPersistenceOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.album.domain.service.AlbumService;
import com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.persistence.output.AlbumMapper;
import com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.persistence.output.AlbumPersistenceOutputAdapter;
import com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.persistence.output.AlbumRepository;
import com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.rest.output.AlbumRestOutputAdapter;
import com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.rest.output.AlbumRestOutputRepository;
import com.bcnc.java.developer.test.jgregoriotest.photo.application.ports.output.PhotoRestOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.photo.domain.service.PhotoService;
import com.bcnc.java.developer.test.jgregoriotest.photo.infrastructure.adapters.output.rest.PhotoRestOutputAdapter;
import com.bcnc.java.developer.test.jgregoriotest.photo.infrastructure.adapters.output.rest.PhotoRestOutputRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public AlbumPersistenceOutputAdapter albumPersistenceOutputAdapter(
            AlbumRepository albumRepository, AlbumMapper albumMapper) {
        return new AlbumPersistenceOutputAdapter(albumRepository, albumMapper);
    }

    @Bean
    public AlbumRestOutputAdapter albumRestOutputAdapter(AlbumRestOutputRepository albumRepository) {
        return new AlbumRestOutputAdapter(albumRepository);
    }

    @Bean
    public PhotoRestOutputAdapter photoRestOutputPort(PhotoRestOutputRepository photoRepository) {
        return new PhotoRestOutputAdapter(photoRepository);
    }

    @Bean
    public AlbumService albumService(SaveAlbumPersistenceOutputPort albumPersistenceOutputPort,
                                     GetAlbumsPersistenceOutputPort getAlbumsPersistenceOutputPort,
                                     GetAlbumsRestOutputPort albumRestOutputPort) {
        return new AlbumService(albumPersistenceOutputPort, getAlbumsPersistenceOutputPort, albumRestOutputPort);
    }

    @Bean
    public PhotoService photoService(PhotoRestOutputPort photoRestOutputPort) {
        return new PhotoService(photoRestOutputPort);
    }
}
