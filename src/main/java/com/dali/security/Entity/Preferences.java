package com.dali.security.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulaire;

    @ManyToOne
     User user;

    @OneToMany (mappedBy = "preferences")
    @JsonIgnore
    List<Technologies> technologies;


}
