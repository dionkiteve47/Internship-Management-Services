    package com.example.intershipmanagement.entities;


    import com.example.intershipmanagement.entities.enumerations.TypeChat;
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import jakarta.persistence.*;
    import lombok.*;
    import lombok.experimental.FieldDefaults;
    import org.springframework.web.multipart.MultipartFile;

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
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private  String titre;
        private  Boolean isCrypted;
        @Enumerated
        private TypeChat type;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonIgnoreProperties("chat")
        @ToString.Exclude
        @OrderBy("date ASC")
        Set<Message> messages;

        @ManyToMany(mappedBy="chats",fetch = FetchType.LAZY)
        @JsonIgnore()
        @ToString.Exclude
        private Set<User> users;






    }
