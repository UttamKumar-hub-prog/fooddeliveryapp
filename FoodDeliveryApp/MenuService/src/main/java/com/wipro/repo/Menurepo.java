package com.wipro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.wipro.entities.Menu;

public interface Menurepo extends JpaRepositoryImplementation<Menu, Long> {

	List<Menu> findByResId(Long resId);

	Menu findByitemName(String name);

	

}
