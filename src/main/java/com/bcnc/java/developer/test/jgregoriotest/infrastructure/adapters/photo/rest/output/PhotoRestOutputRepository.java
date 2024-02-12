package com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.photo.rest.output;

import com.bcnc.java.developer.test.jgregoriotest.domain.model.Photo;

import java.util.Set;

public interface PhotoRestOutputRepository {

    Set<Photo> getAll();

}
