package com.example.SocialMediaVinay.Repository;

import com.example.SocialMediaVinay.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
