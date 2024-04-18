package com.dali.security.Repository;

import com.dali.security.Entity.Technologies;
import org.springframework.data.repository.CrudRepository;

public interface TechRepository extends CrudRepository<Technologies,Long> {
}
