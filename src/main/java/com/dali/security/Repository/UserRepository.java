package com.dali.security.Repository;

import com.dali.security.DTO.Etudiant;
import com.dali.security.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
    @Query("select u from User u where u.exp_mdp  =:threeDaysLater")
    List<User> findUserByExpiredMdp(@Param("threeDaysLater") Date threeDaysLater);

    @Query("select u from User u where u.roles = 'ETUDIANT'")
    List<User>  allEtudiants();
    @Query("select u from User u where u.roles = 'ENSEIGNANT'")
    List<User>  allEnseignants();

    @Query("select u from User u where u.roles = 'REPRESENTANT'")
    List<User>  allRepresentants();

    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.etat = TRUE WHERE u.email = ?1")
    void enableUser(String email);

    // Salim work :
    List<User> findByFirstnameContainingOrLastnameContaining(String nom, String prenom);
    boolean existsByEmail(String email);
    User findByFirstname(String nomUser);

    List<User> findByOnlineFalse();
    //
}
