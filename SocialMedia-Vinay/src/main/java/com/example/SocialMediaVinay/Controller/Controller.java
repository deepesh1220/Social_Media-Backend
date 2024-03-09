package com.example.SocialMediaVinay.Controller;

import com.example.SocialMediaVinay.Dto.Request.AddPostDto;
import com.example.SocialMediaVinay.Dto.Request.AddUserDto;
import com.example.SocialMediaVinay.Dto.Response.PostResponseDto;
import com.example.SocialMediaVinay.Dto.Response.UserResponseDto;
import com.example.SocialMediaVinay.Model.User;
import com.example.SocialMediaVinay.Repository.UserRepository;
import com.example.SocialMediaVinay.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    Service service;


    @PostMapping("/add-user")
    public UserResponseDto addUser(@RequestBody AddUserDto user){
        return service.addUser(user);
    }

    @GetMapping("/get-all-user")
    public List<UserResponseDto> getAllUser(){
        return service.getAllUser();
    }

    @GetMapping("/get-user-by-id")
    public UserResponseDto getUserById(@RequestParam int userId){
        return service.getUserById(userId);
    }

    @PostMapping("/add-post")
    public PostResponseDto addPostByUser(@RequestParam int id , @RequestBody AddPostDto post){
        return service.addPostByUser(id,post);
    }

    @PutMapping("/like")
    public PostResponseDto postLikeButton(@RequestParam int id){
        return  service.postLikeButton(id);
    }
    @GetMapping("/get-all-post-By-User")
    public List<PostResponseDto> getAllPostByUser(@RequestParam int uerId){

        return service.getAllPostByUser(uerId);
    }
    @GetMapping("/get-Highiestlike")
    public PostResponseDto hightiestLike(){
        return service.hightiestLike();
    }
    @GetMapping("/get-Totallikes-In-User")
    public UserResponseDto totalLikes(@RequestParam int userId){
        return service.totalLikes(userId);
    }
    @DeleteMapping("/delete-user")
    public UserRepository deleteUser(@RequestParam int userId){
        return service.deleteUser(userId);
    }

    @PutMapping("/update-name&age")
    public UserResponseDto updateUser(@RequestParam int userId, @RequestParam String newName , @RequestParam int newAge){
        return service.updateUser(userId ,newName, newAge);
    }
    @PutMapping("/update-post")
    public PostResponseDto updatePost(@RequestParam int userId , @RequestParam String newPostName){
        return service.updatePost(userId,newPostName);
    }
}
