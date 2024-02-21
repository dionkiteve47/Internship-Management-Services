package com.example.internshipmanagement.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Formation implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    String titre;
    String sujet;
    String description;
    Integer rating=0;



    @OneToMany(mappedBy = "formationvideo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Video> videoList;

    @OneToMany(mappedBy = "formationsupport", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Support> supportList;



    @OneToMany(mappedBy = "formationquizz", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Quizz> quizzList;


}
