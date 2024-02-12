package com.bcnc.java.developer.test.jgregoriotest.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
public enum SourceDataType {
    EXTERNAL("External"),
    INTERNAL("Internal");
    private static final SourceDataType[] VALUES = values();
    private final  String type;
}
