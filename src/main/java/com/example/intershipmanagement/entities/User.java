package com.example.intershipmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private boolean online;

    private String nomUser;

    private String prenomUser;

    private String password;

    private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore()
    private Set<Chat> chats;

    private long offlineTimeInSeconds;




}
