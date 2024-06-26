package com.example.intershipmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

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

     String Titre;
     LocalDate creationDatesupport;
     String Description;


    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    Formation formationsupport;

}
