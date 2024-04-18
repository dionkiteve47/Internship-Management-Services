package com.dali.security.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
     private String token ;
     @Enumerated (EnumType.STRING)
     private TokenType tokenType;
     private boolean expired;
     private boolean  revoked ;

     @ManyToOne
     @JoinColumn(name = "user_id")
     private User user;


}
