package com.example.intershipmanagement.repositories;
import com.example.intershipmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByNomUserContainingOrPrenomUserContaining(String nom, String prenom);

    boolean existsByEmail(String email);
    User findByNomUser(String nomUser);


    Optional<User> findByEmail(String email);

    List<User> findByOnlineFalse();
}
