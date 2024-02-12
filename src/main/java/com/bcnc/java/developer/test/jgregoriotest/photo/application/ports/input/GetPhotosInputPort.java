package com.bcnc.java.developer.test.jgregoriotest.photo.application.ports.input;

import com.bcnc.java.developer.test.jgregoriotest.photo.domain.model.Photo;

import java.util.Set;

public interface GetPhotosInputPort {
    Set<Photo> getPhotos();

}
