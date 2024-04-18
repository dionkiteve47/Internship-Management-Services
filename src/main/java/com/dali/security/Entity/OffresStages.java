package com.dali.security.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OffresStages implements Serializable {
    @Id
    @GeneratedValue
    private Long idoff;
    String nomE;
    String logo;
    String url;
    String adresse;
    String descOffre;
    String Profile;
    String tecnoligie;
    Date datePost;
    Date datefin;
    int starRating;
    String etat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="offresstages")
    private Set<Demandes> Demandess;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "offres_technologies",
            joinColumns = @JoinColumn(name = "offre_id"),
            inverseJoinColumns = @JoinColumn(name = "technologie_id"))
    private Set<Technologies> technologiess = new HashSet<>();





}
