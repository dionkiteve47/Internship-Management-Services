package com.dali.security.Service;

import com.dali.security.Entity.Quizz;

import java.util.List;
public interface QuizzInterface {
    Quizz addQuizz(Quizz q);

    Quizz updateQuizz(Quizz q);

    Quizz retrieveQuizzByID(Long id);

    void deleteQuizz(Long id);

    List<Quizz> retrieveAllQuizz();

    List<Quizz> getQuizzsByFormationQuizzId(Long formationQuizzId);

    Quizz AddQuizzAndAssign(Quizz quizz, long id);


}
