package com.wipro.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Restuarant")
public interface MenuInterface {
	
     
	@GetMapping("/getResIdByName/{name}")
    Long getResIdByName(@PathVariable String name);

}
