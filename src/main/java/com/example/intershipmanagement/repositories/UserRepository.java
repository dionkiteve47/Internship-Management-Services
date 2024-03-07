package com.example.intershipmanagement.repositories;
import com.example.intershipmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByNomUserContainingOrPrenomUserContaining(String nom, String prenom);


}
