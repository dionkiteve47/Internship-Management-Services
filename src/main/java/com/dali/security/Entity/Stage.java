package com.dali.security.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)


public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private Date startDate;
    private Date endDate;
    private String subject;
    private String level;

    @Transient// This annotation indicates that this field is not persisted in the database
    private int duration; // This field will be calculated based on start and end dates

    // Getters and setters

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        calculateDuration();
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        calculateDuration();
    }

    // Method to calculate duration based on start and end dates
    private void calculateDuration() {
        if (startDate != null && endDate != null) {
            long diffInMilliseconds = Math.abs(endDate.getTime() - startDate.getTime());
            duration = (int) (diffInMilliseconds / (1000 * 60 * 60 * 24)); // Convert milliseconds to days
        } else {
            duration = 0; // If start or end date is not set, duration will be 0
        }
    }


    @OneToOne(mappedBy = "stage", cascade = CascadeType.ALL)
    @JsonIgnore
    private SuiviStage suiviStage;
}
