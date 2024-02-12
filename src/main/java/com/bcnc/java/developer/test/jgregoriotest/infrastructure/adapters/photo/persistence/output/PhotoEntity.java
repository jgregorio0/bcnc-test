package com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.photo.persistence.output;

import com.bcnc.java.developer.test.jgregoriotest.infrastructure.adapters.album.persistence.output.AlbumEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(insertable=false, updatable=false)
    private Long albumId;
    private String title;
    private String url;
    private String thumbnailUr;

    @ManyToOne
    @JoinColumn(name = "albumId")
    private AlbumEntity album;
}
