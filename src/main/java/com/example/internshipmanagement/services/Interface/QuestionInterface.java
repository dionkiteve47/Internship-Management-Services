package com.example.internshipmanagement.services.Interface;

import com.example.internshipmanagement.entities.Question;
import com.example.internshipmanagement.entities.Support;

import java.util.List;
public interface QuestionInterface {
    Question addQuestion(Question q);

    Question updateQuestion(Question q);

    Question retrieveQuestionByID(Long id);

    void deleteQuestion(Long id);

    List<Question> retrieveAllQuestion();

    List<Question> getQuestionsByQuizzQuestionId(Long formationVideoId);


    Question AddQuestionAndAssign(Question question, long id);

}
