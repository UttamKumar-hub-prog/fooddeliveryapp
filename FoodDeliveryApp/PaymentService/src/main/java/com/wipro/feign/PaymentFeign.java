package com.wipro.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name="BookingService")
public interface PaymentFeign {

	

	@GetMapping("/findOrderById/{id}")
	Boolean findOrderById(@PathVariable Long id);
	
	@GetMapping("/getPrice/{id}")
	public Integer getPrice(@PathVariable Long id);
	
	@GetMapping("/getCustomerEmail/{id}")
	public String getCustomerEmail(@PathVariable Long id);

}
