package com.dali.security.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SuiviStage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String step1DocumentPath;
    private ValidationStatus step1ValidationStatus;

    private String step2DocumentPath;
    private ValidationStatus step2ValidationStatus;

    private String step3DocumentPath;
    private ValidationStatus step3ValidationStatus;

    private String step4DocumentPath;
    private ValidationStatus step4ValidationStatus;




    @OneToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;



}









