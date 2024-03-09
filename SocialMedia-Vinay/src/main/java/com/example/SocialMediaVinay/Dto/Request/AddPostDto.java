package com.example.SocialMediaVinay.Dto.Request;

import com.example.SocialMediaVinay.Model.Notification;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddPostDto {

    String content;
}
