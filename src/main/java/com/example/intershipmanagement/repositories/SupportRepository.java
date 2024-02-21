package com.example.intershipmanagement.repositories;


import com.example.intershipmanagement.entities.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SupportRepository extends JpaRepository<Support,Long> {
}
