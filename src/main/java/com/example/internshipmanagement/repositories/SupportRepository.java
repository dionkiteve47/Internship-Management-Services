package com.example.internshipmanagement.repositories;


import com.example.internshipmanagement.entities.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SupportRepository extends JpaRepository<Support,Long> {


}
