package com.example.internshipmanagement.repositories;

import com.example.internshipmanagement.entities.Question;

import com.example.internshipmanagement.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    List<Question> findByQuizzquestion_Id(Long quizzQuestionId);


}