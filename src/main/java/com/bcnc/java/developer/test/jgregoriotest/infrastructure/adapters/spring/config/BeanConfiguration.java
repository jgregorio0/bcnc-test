package com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.spring.config;

import com.bcnc.java.developer.test.jgregoriotest.application.album.ports.output.AlbumPersistenceOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.application.album.ports.output.AlbumRestOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.application.photo.ports.output.PhotoRestOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.domain.service.AlbumService;
import com.bcnc.java.developer.test.jgregoriotest.domain.service.PhotoService;
import com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.album.persistence.output.AlbumMapper;
import com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.album.persistence.output.AlbumPersistenceOutputAdapter;
import com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.album.persistence.output.AlbumRepository;
import com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.album.rest.output.AlbumRestOutputAdapter;
import com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.album.rest.output.AlbumRestOutputRepository;
import com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.photo.rest.output.PhotoRestOutputAdapter;
import com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.photo.rest.output.PhotoRestOutputRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion BEANS
 */
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
    public AlbumService albumService(AlbumPersistenceOutputPort albumPersistenceOutputPort,
                                     AlbumRestOutputPort albumRestOutputPort) {
        return new AlbumService(albumPersistenceOutputPort, albumRestOutputPort);
    }
    @Bean
    public PhotoService photoService(PhotoRestOutputPort photoRestOutputPort) {
        return new PhotoService(photoRestOutputPort);
    }
}
