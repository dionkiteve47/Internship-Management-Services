package com.dali.security.Service;

import com.dali.security.Entity.Question;

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
