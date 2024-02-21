package com.example.internshipmanagement.repositories;


import com.example.internshipmanagement.entities.Quizz;
import com.example.internshipmanagement.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizzRepository extends JpaRepository<Quizz,Long> {

    List<Quizz> findByFormationquizz_Id(Long formationQuizzId);

}