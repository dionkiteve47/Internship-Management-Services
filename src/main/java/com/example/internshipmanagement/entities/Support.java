package com.example.internshipmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Support  implements Serializable {

     @Id
     @GeneratedValue (strategy = GenerationType.IDENTITY)
     Long id;

     String titre;
     String description;
     LocalDate creationDateSupport;


    @ManyToOne
    private Formation formationsupport;


}
