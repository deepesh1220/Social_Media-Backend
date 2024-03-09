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
public class User {
//    User = UserId, Name, Age, List<Post>, List<Notification>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    Integer age;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<UserPost> postList;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    List<Notification> notifications;
}
