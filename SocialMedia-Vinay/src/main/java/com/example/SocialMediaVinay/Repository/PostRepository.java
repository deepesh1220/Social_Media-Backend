package com.example.SocialMediaVinay.Repository;

import com.example.SocialMediaVinay.Model.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<UserPost,Integer> {

//    List<UserPost> getPostList();
}
