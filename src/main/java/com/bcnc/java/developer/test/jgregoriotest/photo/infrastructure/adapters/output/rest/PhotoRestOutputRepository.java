package com.bcnc.java.developer.test.jgregoriotest.photo.infrastructure.adapters.output.rest;

import com.bcnc.java.developer.test.jgregoriotest.photo.domain.model.Photo;

import java.util.Set;

public interface PhotoRestOutputRepository {

    Set<Photo> getAll();

}
