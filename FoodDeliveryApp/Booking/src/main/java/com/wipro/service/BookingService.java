package com.wipro.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.entities.Booking;
import com.wipro.entities.Notification;
import com.wipro.exception.ResourceNotFoundException;
import com.wipro.feign.BookingFeign;
import com.wipro.feign.Mfeign;
import com.wipro.feign.Notificationfeign;
import com.wipro.repo.BookingRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService {
    
	private final BookingRepo bookingrepo;
	private final BookingFeign bfeign;
	private final Mfeign mfeign;
	private final Notificationfeign nfeign;
	
	
	public Booking orderFood(Booking booking) {
        log.info("Order request received: {}", booking);

	    Long resid = bfeign.getResIdByName(booking.getResName());
	    if (resid == null) {
            log.error("Restaurant not found: {}", booking.getResName());

	        throw new ResourceNotFoundException("Restaurant not found: " + booking.getResName());
	    }

	   
	    Integer quantity = booking.getQuantity();
	    Integer price = mfeign.getPriceByName(booking.getItemName());
	    
	    Notification note=new Notification();
	    note.setToEmail(booking.getCustomerEmail());
	    note.setMessage("Order saved success");
	    note.setSubject("about order");
	    
	    nfeign.sendNotification(note);

	    if (quantity == null || price == null) {
            log.error("Price or quantity is missing for item: {}", booking.getItemName());

	        throw new ResourceNotFoundException("Price or quantity is missing for item: " + booking.getItemName());
	    }

	    
	    booking.setPrice(price * quantity);

	    

	    return bookingrepo.save(booking);
	}


	public List<Booking> getorders() {
        log.info("Fetching all orders");

		return bookingrepo.findAll();
	}

	public Booking getOrderById(Long id) {
        log.info("Fetching order by ID: {}", id);

		
		return bookingrepo.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
	}

	public Booking updateOrderById(Long id, Booking booking) {
        log.info("Updating order with ID: {}", id);

		Booking existing=bookingrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));;
		
				if (booking.getCustomerName() != null) {
			        existing.setCustomerName(booking.getCustomerName());
			    }
			    if (booking.getPhone() != null) {
			        existing.setPhone(booking.getPhone());
			    }
			    if (booking.getResName() != null) {
			        existing.setResName(booking.getResName());
			    }
			    if (booking.getCustomerEmail()!= null) {
			        existing.setCustomerEmail(booking.getCustomerEmail());
			    }
			    if (booking.getItemName() != null) {
			        existing.setItemName(booking.getItemName());
			    }
			    if (booking.getQuantity() != null) {
			        existing.setQuantity(booking.getQuantity());
			    }
			    
		        log.info("Order updated successfully for ID: {}", existing);

			    return bookingrepo.save(existing);
	}

	public Integer getAmount(Long id) {
        log.info("Calculating amount for order ID: {}", id);

	    Booking booking = bookingrepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

	    Integer quantity = booking.getQuantity();
	    Integer price = mfeign.getPriceByName(booking.getItemName());

	    if (quantity == null || price == null) {
            log.error("Price or quantity missing for order ID: {}", id);

	        throw new RuntimeException("Price or quantity is missing for booking id: " + id);
	    }
        log.info("Total amount for order ID {}: {}", id);

	    return price * quantity;
	}

	public Boolean findOrderById(Long id) {
        log.debug("Checking existence of order ID: {}", id);

	    return bookingrepo.existsById(id);
	}


	public Integer getPrice(Long id) {
        log.info("Fetching price for order ID: {}", id);

		Booking booking = bookingrepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
        log.info("Price for order ID {}: {}", id, booking.getPrice());

		Integer price=booking.getPrice();
		return price;
	}


	public String getCustomerEmail(Long id) {
        log.info("Fetching customer email for order ID: {}", id);

		Booking booking = bookingrepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
        log.info("Customer email for order ID {}: {}", id, booking.getCustomerEmail());

		return booking.getCustomerEmail();
	}
    
	
	
}
