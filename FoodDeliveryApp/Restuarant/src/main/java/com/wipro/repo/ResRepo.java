package com.wipro.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import com.wipro.entities.Restuarant;

public interface ResRepo extends JpaRepositoryImplementation<Restuarant,Long> {



	@Query("SELECT r.resId FROM Restuarant r WHERE r.name = :name")
	Long findIdByName(@Param("name") String name);


}
