package com.wipro.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="MenuService")
public interface Mfeign {
	@GetMapping("/getPriceByName/{name}")
	Integer getPriceByName(@PathVariable String name);
}
