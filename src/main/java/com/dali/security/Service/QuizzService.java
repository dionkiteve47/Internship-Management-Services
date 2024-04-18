package com.dali.security.Service;

import com.dali.security.Entity.*;
import com.dali.security.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class QuizzService implements QuizzInterface {

            FormationRepository formationRepository;
            SupportRepository supportRepository;
            VideoRepository videoRepository;
            QuizzRepository quizzRepository;
            QuestionRepository questionRepository;


    @Override
    public Quizz addQuizz(Quizz q) {
        return quizzRepository.save(q);
    }

    @Override
    public Quizz updateQuizz(Quizz q) {
        return quizzRepository.save(q);
    }

    @Override
    public Quizz retrieveQuizzByID(Long id) {
        return quizzRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteQuizz(Long id) {
        quizzRepository.deleteById(id);
    }

    @Override
    public List<Quizz> retrieveAllQuizz() {
        return quizzRepository.findAll();
    }


    //*******************AVANCEES*****************//

    public List<Quizz> getQuizzsByFormationQuizzId(Long formationQuizzId) {
        return quizzRepository.findByFormationquizz_Id(formationQuizzId);
    }


    @Override
    public Quizz AddQuizzAndAssign(Quizz quizz, long id) {
        Formation formation = formationRepository.findById(id).get();
        quizz.setFormationquizz(formation);
        return quizzRepository.save(quizz);

    }


}





