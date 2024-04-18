package com.dali.security.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity


@Table(name= "user")

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String firstname;

    private String lastname;
    @Column(unique = true)



    private String email;

    private String password ;
    @Enumerated(EnumType.STRING)
    private Role roles ;

    private boolean etat=false;

    private Date exp_mdp;
    @Enumerated(EnumType.STRING)
    private  Classe classe;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    List<Token> tokens;

    @OneToMany(mappedBy="user")

    List<Preferences> preferences;

    String nom_entreprise;

    private boolean mfaEnabled;
    private String secret;


    // Salim Attributs
    private boolean online;
    private long offlineTimeInSeconds;
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore()
    private Set<Chat> chats;
    //


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return (exp_mdp.after(new Date()));
    }

    @Override
    public boolean isEnabled() {
        return etat;
    }



}
