package com.example.SocialMediaVinay.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String content;

    String time;

    int likeNo;

//    @OneToMany(mappedBy = "id" , cascade = CascadeType.ALL)
//    List<Notification> notifications;

    @ManyToOne
    User user;

}
