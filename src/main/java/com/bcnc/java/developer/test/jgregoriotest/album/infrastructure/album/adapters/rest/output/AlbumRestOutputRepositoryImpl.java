package com.bcnc.java.developer.test.jgregoriotest.album.infrastructure.album.adapters.rest.output;

import com.bcnc.java.developer.test.jgregoriotest.album.domain.model.Album;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Repository
@RequiredArgsConstructor
public class AlbumRestOutputRepositoryImpl implements AlbumRestOutputRepository {

    private final RestTemplate restTemplate;


    @Override
    public Set<Album> getAll() {
        String url = "https://jsonplaceholder.typicode.com/albums";
        return restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Set<Album>>() {})
                .getBody();
    }
}
