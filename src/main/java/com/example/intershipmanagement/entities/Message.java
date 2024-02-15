package com.example.intershipmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String contenu;
    private Date sent_Date;
    private Boolean isSeen;
    private Date seen_Date;
    private Boolean isLiked;

}
