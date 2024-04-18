package com.dali.security.Service;

import com.dali.security.Entity.Video;

import java.util.List;

public interface VideoInterface {

    Video addVideo(Video v);
    Video updateVideo(Video v);

    List<Video> retrieveAllVideo();

    Video retrieveVideoByID(Long id);

    void deleteVideo(Long id);


     List<Video> getVideosByFormationVideoId(Long formationVideoId);

    Video AddVideoAndAssign(Video video,long id);

    List<Video> retrieveMostLikedVideos(); // Add this method

}

