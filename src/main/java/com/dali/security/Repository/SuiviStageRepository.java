package com.dali.security.Repository;

import com.dali.security.Entity.SuiviStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SuiviStageRepository extends JpaRepository<SuiviStage, Long> {


}
