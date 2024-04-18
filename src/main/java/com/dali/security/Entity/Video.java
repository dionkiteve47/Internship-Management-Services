package com.dali.security.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Video  implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    String titre;
    String description;
    Double longueur;
    Integer ordre;
    Integer likes=0;
    LocalDate creationDateVideo;


    @ManyToOne
    private Formation formationvideo;



}
