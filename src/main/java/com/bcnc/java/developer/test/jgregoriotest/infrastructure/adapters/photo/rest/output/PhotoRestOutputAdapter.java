package com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.photo.rest.output;

import com.bcnc.java.developer.test.jgregoriotest.application.photo.ports.output.PhotoRestOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.domain.model.Photo;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class PhotoRestOutputAdapter implements PhotoRestOutputPort {

    private final PhotoRestOutputRepository photoRepository;

    @Override
    public Set<Photo> getPhotos() {
        return photoRepository.getAll();
    }
}
