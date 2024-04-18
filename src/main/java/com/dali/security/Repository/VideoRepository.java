package com.dali.security.Repository;

import com.dali.security.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video,Long> {
    List<Video> findByFormationvideo_Id(Long formationVideoId);

    List<Video> findTop3ByOrderByLikesDesc(); // Add this method
}
