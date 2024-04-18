package com.example.internshipmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
@Service
public class StreamService {

    @Autowired
    private ResourceLoader resourceLoader;

    private static final String FILE_PATH = "classpath:uploads/%s.mp4";

    public Mono<Resource> retrieveContent(String titre){
        return Mono.fromSupplier(() -> resourceLoader.getResource(String.format(FILE_PATH, titre)));
    }
}
