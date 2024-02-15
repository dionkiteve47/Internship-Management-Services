package com.example.intershipmanagement.entities;


import com.example.intershipmanagement.entities.enumerations.TypeChat;
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
public class Chat implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private  Boolean isCrypted;
    @Enumerated
    private TypeChat type;

    @OneToMany
    Set<Message> messages;
    @ManyToMany(mappedBy="chats", cascade = CascadeType.ALL)
    private Set<User> users;





}
