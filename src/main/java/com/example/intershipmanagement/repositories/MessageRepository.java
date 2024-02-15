package com.example.intershipmanagement.repositories;


import com.example.intershipmanagement.entities.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Chat,Long> {
}
