package com.example.SocialMediaVinay.Service;


import com.example.SocialMediaVinay.Dto.Request.AddPostDto;
import com.example.SocialMediaVinay.Dto.Request.AddUserDto;
import com.example.SocialMediaVinay.Dto.Response.PostResponseDto;
import com.example.SocialMediaVinay.Dto.Response.UserResponseDto;
import com.example.SocialMediaVinay.Model.Notification;
import com.example.SocialMediaVinay.Model.User;
import com.example.SocialMediaVinay.Model.UserPost;
import com.example.SocialMediaVinay.Repository.NotificationRepository;
import com.example.SocialMediaVinay.Repository.PostRepository;
import com.example.SocialMediaVinay.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    NotificationRepository notificationRepository;

    public UserResponseDto addUser(AddUserDto userDto) {
        User u = new User();
        u.setName(userDto.getName());
        u.setAge(userDto.getAge());
        User userRes = userRepository.save(u);
        UserResponseDto response = new UserResponseDto();
        response.setId(userRes.getId());
        response.setName(userRes.getName());
        response.setAge(userRes.getAge());
        return response;
    }

    public List<UserResponseDto> getAllUser() {
        List<UserResponseDto> allUser = new ArrayList<>();

        List<User> us = userRepository.findAll();

        for(User u : us){
            UserResponseDto userRes = new UserResponseDto();
            userRes.setId(u.getId());
            userRes.setName(u.getName());
            userRes.setAge(u.getAge());
            allUser.add(userRes);
        }
        return allUser;
    }

    public UserResponseDto getUserById(int userId) {

        User us = userRepository.findById(userId).get();
        UserResponseDto res = new UserResponseDto();
        res.setId(us.getId());
        res.setName(us.getName());
        res.setAge(us.getAge());

        return res;
//        return  userRepository.findById(userId).get();
    }
    public PostResponseDto addPostByUser(int id , AddPostDto addPostDto) {
        Date d = new Date();
        //Post
        UserPost userPost = new UserPost();
        //User
        User user = userRepository.findById(id).get();
        List<Notification> list = user.getNotifications();
        List<UserPost> postList = user.getPostList();

        //new notification
        Notification noti = new Notification();
        noti.setMsg(addPostDto.getContent()+" added new post");
        noti.setTime(d.toString());


        list.add(notificationRepository.save(noti));
        user.setNotifications(list);

        userPost.setUser(user);
        userPost.setLikeNo(0);
        userPost.setContent(addPostDto.getContent());

        userPost.setTime(d.toString());
        UserPost postRes = postRepository.save(userPost);
        postList.add(postRes);
        userRepository.save(user);


        PostResponseDto response = new PostResponseDto();
        response.setId(postRes.getId());
        response.setContent(postRes.getContent());
        response.setTimes(postRes.getTime());
        return response;
    }


    public PostResponseDto postLikeButton(int id) {

        UserPost userPost = postRepository.findById(id).get();
        int like = userPost.getLikeNo()+1;
        userPost.setLikeNo(like);
        UserPost response = postRepository.save(userPost);

        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setId(response.getId());
        postResponseDto.setContent(response.getContent());
        postResponseDto.setLike(response.getLikeNo());
        postResponseDto.setTimes(new Date().toString());

        Notification noti = new Notification();
        noti.setMsg(userPost.getContent()+" Post has been liked by ");
        noti.setTime(new Date().toString());

        User user = userPost.getUser();
        List<Notification> newNoti = user.getNotifications();
        newNoti.add(notificationRepository.save(noti));
        userRepository.save(user);

        return postResponseDto;
    }

    public List<PostResponseDto> getAllPostByUser(int userId) {

//        String s = "select id,content,like_no,time from social_media where user_id = 1 ";
        User user = userRepository.findById(userId).get();
        List<UserPost> allPost =user.getPostList();
        List<PostResponseDto> postResponse = new ArrayList<>();

        for(UserPost u : allPost)
        {
            PostResponseDto postRes = new PostResponseDto();
            postRes.setId(u.getId());
            postRes.setContent(u.getContent());
            postRes.setLike(u.getLikeNo());
            postRes.setTimes(u.getTime());
            postResponse.add(postRes);
        }
        return postResponse;
    }

    public PostResponseDto hightiestLike() {

          List<User> userList = new ArrayList<>();
        List<UserPost> userPost = postRepository.findAll();
        User liker = new User();

        PostResponseDto postRes = new PostResponseDto();
        UserResponseDto userRes = new UserResponseDto();

        int maxLikes =0;
        for( UserPost up : userPost)
        {
            if(up.getLikeNo()> maxLikes){

                maxLikes = up.getLikeNo();
                postRes.setId(up.getId());
                postRes.setContent(up.getContent());
                postRes.setLike(up.getLikeNo());
                postRes.setTimes(up.getTime());
            }
        }
        System.out.println(" Maximum like is "+maxLikes);

        return postRes;
    }
    public UserResponseDto totalLikes(int userId){
        User us = userRepository.findById(userId).get();
        UserPost  userPost = postRepository.findById(userId).get();
        userPost.setLikeNo(userId);
        Optional<UserPost> userPostList1 = postRepository.findById(userId);
        List<UserPost> userPostList = postRepository.findAll();
        UserResponseDto userRes = new UserResponseDto();

        int sumOfLike=0;
        for(UserPost up : userPostList )
        {
            sumOfLike = up.getLikeNo()+sumOfLike;
//            userRes.setId(userPost.getId());
//            userRes.setName(userRes.getName());
//            userRes.setAge(userRes.getAge());
//            userRes.setLikes(userPost.getLikeNo());
        }
        return userRes;
    }

    public UserRepository deleteUser(int userId) {

        User user = userRepository.findById(userId).get();
        if(user != null){
            userRepository.deleteById(userId);
            System.out.println(" User id is deleted");
        }
        else
            System.out.println(" User is not available");
        return null;
    }

    public UserResponseDto updateUser(int userId, String newName , int newAge) {

        UserResponseDto userRespDto = new UserResponseDto();
        User user = userRepository.findById(userId).get();

        user.setName(newName);
        user.setAge(newAge);
        userRepository.save(user);
        userRespDto.setId(userId);
        userRespDto.setName(user.getName());
        userRespDto.setAge(user.getAge());

        return userRespDto;
    }

    public PostResponseDto updatePost(int userId, String newPostName) {

        Date d = new Date();
        PostResponseDto postResponseDto = new PostResponseDto();
        UserPost userPost = postRepository.findById(userId).get();
        userPost.setContent(newPostName);
        userPost.setTime(d.toString());
        postRepository.save(userPost);
        postResponseDto.setId(userPost.getId());
        postResponseDto.setContent(userPost.getContent());
        postResponseDto.setTimes(userPost.getTime());
        postResponseDto.setLike(userPost.getLikeNo());
        return postResponseDto;
    }
}
