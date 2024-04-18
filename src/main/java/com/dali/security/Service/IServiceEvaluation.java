package com.dali.security.Service;

import com.dali.security.Entity.Evaluation;

public interface IServiceEvaluation {

    double calculateFinalMark(Evaluation evaluation);
    public Evaluation saveEvaluation(Evaluation evaluation);
}
