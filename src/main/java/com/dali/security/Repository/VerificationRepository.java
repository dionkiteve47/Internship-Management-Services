package com.dali.security.Repository;

import com.dali.security.Entity.Verification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long> {
    Optional<Verification> findByToken(String token);
    @Transactional
    @Modifying
    @Query("UPDATE Verification v " +
            "SET v.confirmedAt = ?2 " +
            "WHERE v.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);
}
