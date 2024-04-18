package com.dali.security.Service;

import com.dali.security.Entity.Formation;
import com.dali.security.Entity.Video;
import com.dali.security.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoService implements VideoInterface {

    FormationRepository formationRepository;
    SupportRepository supportRepository;
    VideoRepository videoRepository;
    QuizzRepository quizzRepository;
    QuestionRepository questionRepository;
    private final Environment environment;

    @Override
    public Video addVideo(Video v) {
        return videoRepository.save(v);
    }

    @Override
    public Video updateVideo(Video v) {
        videoRepository.save(v);
        return v;
    }

    @Override
    public List<Video> retrieveAllVideo() {
        return videoRepository.findAll();
    }

    @Override
    public Video retrieveVideoByID(Long id) {
        return videoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }


    //*******************AVANCEES*****************//

    public List<Video> getVideosByFormationVideoId(Long formationVideoId) {
        return videoRepository.findByFormationvideo_Id(formationVideoId);
    }



    @Override
    public Video AddVideoAndAssign(Video video, long id) {
        video.setCreationDateVideo(LocalDate.now());
        Formation formation = formationRepository.findById(id).get();
        video.setFormationvideo(formation);
        return videoRepository.save(video);

    }

    // New method to retrieve most liked videos
    public List<Video> retrieveMostLikedVideos() {
        return videoRepository.findTop3ByOrderByLikesDesc();
    }


}


