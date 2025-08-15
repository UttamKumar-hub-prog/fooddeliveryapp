package com.wipro.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.entities.Notification;
import com.wipro.entities.Payment;
import com.wipro.exception.PaymentProcessingException;
import com.wipro.exception.ResourceNotFoundException;
import com.wipro.feign.Notificationfeign;
import com.wipro.feign.PaymentFeign;
import com.wipro.repo.paymentrepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j

public class PaymentService {

	private final paymentrepo payrepo;
	private final PaymentFeign payfeign;
	private final Notificationfeign nfeign;

	public Payment placeOrder(Long id) {
		Boolean bookingExists = payfeign.findOrderById(id);
		Integer price = payfeign.getPrice(id);
		String customeremail = payfeign.getCustomerEmail(id);

		if (Boolean.TRUE.equals(bookingExists)) {
			Payment payment = new Payment();
			payment.setDateAndTime(LocalDateTime.now());
			payment.setOrderStatus("order placed");
			payment.setPrice(price);
			payment.setPaymentMethod("cod");

			Notification note = new Notification();
			note.setToEmail(customeremail);
			note.setMessage("Order Placed success");
			note.setSubject("about order");

			nfeign.sendNotification(note);

			return payrepo.save(payment);
		} else {
			log.error("Error while placing order for bookingId {}", id);

			throw new PaymentProcessingException("Booking not found for id: " + id);
		}
	}

	public Payment updateStatus(Long id, Payment newstatus) {
		Payment existing = payrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
		existing.setOrderStatus(newstatus.getOrderStatus());

		String customeremail = payfeign.getCustomerEmail(id);
		Notification note = new Notification();
		note.setToEmail(customeremail);
		note.setMessage(newstatus.getOrderStatus());
		note.setSubject("about order");

		nfeign.sendNotification(note);

		return payrepo.save(existing);
	}

	public List<Payment> getAllOrders() {

		return payrepo.findAll();
	}

}
