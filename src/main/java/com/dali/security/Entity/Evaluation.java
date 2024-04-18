package com.dali.security.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private double academicKnowledge;
    private double criticalThinking;
    private double projectManagement;
    private double professionalDevelopment;
    private double problemSolvingSkills;
    private double feedbackReception;
    private double finalMark;
    private double discipline;
    private double communicationSkills;
    private double problemSolving;
    private double teamwork;
}
