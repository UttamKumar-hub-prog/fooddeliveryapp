package com.wipro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.entities.Payment;
import com.wipro.service.PaymentService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor

public class paymentcontroller {
	
	private final PaymentService paymentservice;
	
	    @GetMapping("/PlaceOrder/{id}")
		public Payment PlaceOrder(@PathVariable Long id)
		{
			return paymentservice.placeOrder(id);
		}
	    
	    @PatchMapping("/updateOrderStatus/{id}")
	    public Payment updateStatus(@PathVariable Long id,@RequestBody Payment newstatus)
	    {
	    	return paymentservice.updateStatus(id,newstatus);
	    }
	    
	    @GetMapping("/getAllOrders")
	    public List<Payment> getAllOrders()
	    {
	    	return paymentservice.getAllOrders();
	    }
	
}
