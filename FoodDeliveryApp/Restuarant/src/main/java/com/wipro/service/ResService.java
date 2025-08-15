package com.wipro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wipro.customexception.RES_NOT_FOUND_EXCEPTION;
import com.wipro.entities.Restuarant;
import com.wipro.repo.ResRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ResService {

	private final ResRepo resrepo;

	public Restuarant createres(Restuarant restuarant) {
		log.info("Creating restaurant: {}", restuarant.getName());
		return resrepo.save(restuarant);
	}

	public Page<Restuarant> getRes(Pageable pageable) {
		log.info("Fetching restaurants with page request: {}", pageable);

		return resrepo.findAll(pageable);
	}

	public void deleteres(Long id) {
		log.warn("Deleting restaurant with id: {}", id);
		
		resrepo.deleteById(id);

	}

	public Long getResIdByName(String name) {
		log.info("Searching for restaurant id by name: {}", name);

		Long id = resrepo.findIdByName(name);
		if (id == null) {
			log.error("Restaurant not found with name: {}", name);

			throw new RES_NOT_FOUND_EXCEPTION("Restaurant not found with name: " + name);
		}
		return id;
	}

	public Restuarant getResById(Long id) {
		log.info("Fetching restaurant by id: {}", id);

		return resrepo.findById(id).orElseThrow(() -> {
			log.error("Restaurant not found with id: {}", id);

			return new RES_NOT_FOUND_EXCEPTION("Restaurant not found with id: " + id);
		});

	}
}
