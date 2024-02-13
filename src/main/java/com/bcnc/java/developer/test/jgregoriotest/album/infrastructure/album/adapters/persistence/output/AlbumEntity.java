package com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.persistence.output;

import com.bcnc.java.developer.test.jgregoriotest.photo.infrastructure.adapters.output.persistence.PhotoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumEntity {// TODO JG Graph needed for photos?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String title;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<PhotoEntity> photos;
}
