package com.bcnc.java.developer.test.jgregoriotest.application.photo.ports.output;

import com.bcnc.java.developer.test.jgregoriotest.domain.model.Photo;

import java.util.Set;

public interface GetPhotosUserCase {
    Set<Photo> getPhotos();

}
