package com.wipro.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.entities.Notification;
import com.wipro.service.Nservice;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class NController {
      private final Nservice nservice;
      
      
      
      @PostMapping("/notifications/send")
      public void sendNotification(@RequestBody Notification request) {
          nservice.sendNotification(request);
      }
 
}
