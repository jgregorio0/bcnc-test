package com.bcnc.java.developer.test.jgregoriotest.photo.domain.service;

import com.bcnc.java.developer.test.jgregoriotest.photo.application.ports.input.GetPhotosInputPort;
import com.bcnc.java.developer.test.jgregoriotest.photo.application.ports.output.PhotoRestOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.photo.domain.model.Photo;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class PhotoService implements GetPhotosInputPort {

    private final PhotoRestOutputPort photoRestOutputPort;

    @Override
    public Set<Photo> getPhotos() {
        return photoRestOutputPort.getPhotos();
    }

}
