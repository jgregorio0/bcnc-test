package com.bcnc.java.developer.test.jgregoriotest.photo.application.ports.output;

import com.bcnc.java.developer.test.jgregoriotest.photo.domain.model.Photo;

import java.util.Set;

public interface PhotoRestOutputPort {
    Set<Photo> getPhotos();
}
