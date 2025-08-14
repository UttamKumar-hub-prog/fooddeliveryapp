package com.wipro.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.wipro.entities.Notification;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Nservice {

	private final JavaMailSender mailSender;
	public void sendNotification(Notification request) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getToEmail());
        message.setSubject(request.getSubject());
        message.setText(request.getMessage());

        mailSender.send(message);	
	}

}
