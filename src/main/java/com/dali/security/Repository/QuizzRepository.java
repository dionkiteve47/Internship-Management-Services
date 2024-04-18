package com.dali.security.Repository;


import com.dali.security.Entity.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizzRepository extends JpaRepository<Quizz,Long> {

    List<Quizz> findByFormationquizz_Id(Long formationQuizzId);

}