package com.example.SocialMediaVinay.Repository;

import com.example.SocialMediaVinay.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
