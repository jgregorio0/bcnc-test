package com.bcnc.java.developer.test.jgregoriotest.photo.domain.model;

import lombok.*;

import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
    private Long albumId;
    private Long id;
    private String title;
    private String url;
    private String thumbnailUr;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(this.id, photo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
