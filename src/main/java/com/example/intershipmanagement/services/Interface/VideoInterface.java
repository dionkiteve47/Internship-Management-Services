package com.example.intershipmanagement.services.Interface;

import com.example.intershipmanagement.entities.Video;

import java.util.List;

public interface VideoInterface {

    Video addVideo(Video v);
    void updateVideo(Video v);

    List<Video> retriveAllVideo();

    Video retriveVideoByID(Long id);

    void deleteVideo(Long id);
}
