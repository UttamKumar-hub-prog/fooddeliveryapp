package com.wipro.controller;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.entities.Menu;
import com.wipro.service.MenuService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MenuController {
	
	private final  MenuService menuservice;
	
	@PostMapping("/additem")
	public Menu createmenu(@RequestBody Menu menu)
	{
		return menuservice.createmenu(menu);
	}
	
	@GetMapping("/getallitems")
	public Page<Menu> getallitems(Pageable pageable)
	{
		return menuservice.getallitems(pageable);
	}
	
	@GetMapping("/getitemsbyresid/{resId}") 
	public List<Menu> getItemsByResId(@PathVariable Long resId) {
	    return menuservice.getItemsByResId(resId);
	}
	
	@GetMapping("/getitemsbyresname/{name}") //getitemsbyresname
	public List<Menu> getItemsByResName(@PathVariable String name)
	{
		return menuservice.getItemsByResName(name);
	}
	
	@PatchMapping("/updateMenuByMenuId/{id}")
	public Menu updateMenuById(@PathVariable Long id , @RequestBody Menu newMenu) {
		return menuservice.updateMenuById(id,newMenu);
	}
	
	@PostMapping("/addItemsByResName/{name}") //addItemsByResName
	public Menu addItemsByResName(@PathVariable String name,@RequestBody Menu menu) {
		return menuservice.addItemsByResName(name,menu);
	}
	
	@GetMapping("/getPriceByName/{name}") //getPriceByItemName
	public Integer getPriceByName(@PathVariable String name) {
		return menuservice.givePrice(name);
	}
	
	
	

}
