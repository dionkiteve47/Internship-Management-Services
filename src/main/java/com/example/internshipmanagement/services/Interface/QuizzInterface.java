package com.example.internshipmanagement.services.Interface;

import com.example.internshipmanagement.entities.Quizz;
import com.example.internshipmanagement.entities.Support;
import com.example.internshipmanagement.entities.Video;

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
