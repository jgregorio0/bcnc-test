package com.bcnc.java.developer.test.jgregoriotest.domain.service;

import com.bcnc.java.developer.test.jgregoriotest.application.photo.ports.output.GetPhotosUserCase;
import com.bcnc.java.developer.test.jgregoriotest.application.photo.ports.output.PhotoRestOutputPort;
import com.bcnc.java.developer.test.jgregoriotest.domain.model.Photo;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class PhotoService implements GetPhotosUserCase {

    private final PhotoRestOutputPort photoRestOutputPort;

    @Override
    public Set<Photo> getPhotos() {
        return photoRestOutputPort.getPhotos();
    }

}
