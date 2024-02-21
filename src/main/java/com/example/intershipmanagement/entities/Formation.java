package com.example.intershipmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @GeneratedValue
    private Long id;
    private String titre;
    private String sujet;
    private String description;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "formation")
    List<Preferences> preferencesList = new ArrayList<>();

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "formationvideo")
    List<Video> videoList = new ArrayList<>();

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "formationsupport")
    List<Support> supportList = new ArrayList<>();
}
