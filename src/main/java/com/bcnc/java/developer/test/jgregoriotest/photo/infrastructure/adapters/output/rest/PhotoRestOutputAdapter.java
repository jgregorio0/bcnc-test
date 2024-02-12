package com.bcnc.java.developer.test.jgregoriotest.photo.infrastructure.adapters.output.rest;

import com.bcnc.java.developer.test.jgregoriotest.photo.application.ports.output.PhotoRestOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.photo.domain.model.Photo;
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
