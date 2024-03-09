package com.example.SocialMediaVinay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialMediaVinayApplication {

	public static void main(String[] args) {
		try{
			SpringApplication.run(SocialMediaVinayApplication.class, args);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

}

//	Create a social media platform
//		User = UserId, Name, Age, List<Post>, List<Notification>
//      Post = Content, Time, User, Like;
//		Notification = Post, Time
//		Description: A user will create a post and will receive notification wrt the post (for likes).
//		POST API - Add a User
//		POST API - Add a Post under user given
//		1.PUT API - Like a given post
//		2.POST API - Send a notification each time a post is liked.
//		3.GET API - Find the most number of notifications received by a user41


// https://github.com/VinayVastrakar/Vaccination-Booking-System