package com.dali.security.Service;

import com.dali.security.Entity.Evaluation;
import com.dali.security.Repository.EvaluationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceEvaluation implements IServiceEvaluation{

    @Autowired
    private EvaluationRepository evaluationRepository;
    @Override
    public double calculateFinalMark(Evaluation evaluation) {
        double totalPoints = 0;

        // Calculate total points based on criteria scores
        totalPoints += getPoints(evaluation.getDiscipline());
        totalPoints += getPoints(evaluation.getCommunicationSkills());
        totalPoints += getPoints(evaluation.getProblemSolving());
        totalPoints += getPoints(evaluation.getTeamwork());
        totalPoints += getPoints(evaluation.getAcademicKnowledge());
        totalPoints += getPoints(evaluation.getCriticalThinking());
        totalPoints += getPoints(evaluation.getProjectManagement());
        totalPoints += getPoints(evaluation.getProfessionalDevelopment());
        totalPoints += getPoints(evaluation.getProblemSolvingSkills());
        totalPoints += getPoints(evaluation.getFeedbackReception());

        // Calculate final mark out of 20
        double finalMark = (totalPoints / 50) * 20; // Maximum possible points is 50
        return finalMark;
    }


    @Override
    public Evaluation saveEvaluation(Evaluation evaluation) {
        // Calculate final mark
        double finalMark = calculateFinalMark(evaluation);

        // Set the final mark in the evaluation object
        evaluation.setFinalMark(finalMark);

        // Save the evaluation object to the database
        return evaluationRepository.save(evaluation);
    }

    private double getPoints(double score) {
        if (score >= 4) {
            return 5;
        } else if (score >= 3) {
            return 4;
        } else if (score >= 2) {
            return 3;
        } else {
            return 1;
        }
    }


    }

