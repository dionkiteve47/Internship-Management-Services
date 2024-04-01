    package com.example.intershipmanagement.entities;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;
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
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String contenu;
        private String imagePath;
        private String filePath;
        private Date sent_Date;
        private Boolean isSeen;
        private Date seen_Date;
        private Boolean isLiked;

        @ManyToOne
        @JoinColumn(name = "chat_id", referencedColumnName = "id")
        @JsonIgnoreProperties("messages")
        private Chat chat;


        private String sender;



    }

