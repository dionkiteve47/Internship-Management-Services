package com.example.intershipmanagement.services.Imlp;

import com.example.intershipmanagement.entities.Video;
import com.example.intershipmanagement.repositories.VideoRepository;
import com.example.intershipmanagement.services.Interface.VideoInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VideoService implements VideoInterface {

    VideoRepository videoRepository;
    @Override
    public Video addVideo(Video v) {
        return videoRepository.save(v);
    }

    @Override
    public void updateVideo(Video v) {
        videoRepository.save(v);
    }

    @Override
    public List<Video> retriveAllVideo() {
        return videoRepository.findAll();
    }

    @Override
    public Video retriveVideoByID(Long id) {
        return videoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }
}
