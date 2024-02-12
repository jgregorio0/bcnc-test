package com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.persistence.output;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long>{
    
}
