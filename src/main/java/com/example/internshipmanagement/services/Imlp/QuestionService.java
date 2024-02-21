package com.example.internshipmanagement.services.Imlp;

import com.example.internshipmanagement.entities.*;
import com.example.internshipmanagement.repositories.*;
import com.example.internshipmanagement.services.Interface.QuestionInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService implements QuestionInterface {

    FormationRepository formationRepository;
    SupportRepository supportRepository;
    VideoRepository videoRepository;
    QuizzRepository quizzRepository;
    QuestionRepository questionRepository;


    @Override
    public Question addQuestion(Question q) {
        return questionRepository.save(q);
    }

    @Override
    public Question updateQuestion(Question q) {
        return questionRepository.save(q);
    }


    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public Question retrieveQuestionByID(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Question> retrieveAllQuestion() {
        return questionRepository.findAll();
    }


    //*******************AVANCEES*****************//
    public List<Question> getQuestionsByQuizzQuestionId(Long quizzQuestionId) {
        return questionRepository.findByQuizzquestion_Id(quizzQuestionId);
    }

    @Override
    public Question AddQuestionAndAssign(Question question, long id) {
        Quizz quizz = quizzRepository.findById(id).get();
        question.setQuizzquestion(quizz);
        return questionRepository.save(question);

    }

}


