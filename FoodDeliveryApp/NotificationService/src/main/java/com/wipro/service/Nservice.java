package com.wipro.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.wipro.entities.Notification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class Nservice {

	private final JavaMailSender mailSender;

	public void sendNotification(Notification request) {
		log.info("Sending notification to: {}", request.getToEmail());

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(request.getToEmail());
		message.setSubject(request.getSubject());
		message.setText(request.getMessage());

		mailSender.send(message);
		log.info("Notification sent successfully to: {}", request.getToEmail());

	}

}
