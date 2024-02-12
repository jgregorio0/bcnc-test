package com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.album.persistence.output;

import com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.photo.persistence.output.PhotoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String title;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<PhotoEntity> photos;
}
