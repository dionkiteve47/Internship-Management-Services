package com.dali.security.Repository;


import com.dali.security.Entity.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SupportRepository extends JpaRepository<Support,Long> {


}
