package com.bcnc.java.developer.test.jgregoriotest.domain.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    private Long userId;
    private Long id;
    private String title;
    private List<Photo> photos = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Album photo = (Album) o;
        return Objects.equals(this.id, photo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
