package com.wipro.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.wipro.entities.Notification;

@FeignClient(name="NotificationService")
public interface Notificationfeign {

	@PostMapping("/notifications/send")
    void sendNotification(Notification notification);
}
