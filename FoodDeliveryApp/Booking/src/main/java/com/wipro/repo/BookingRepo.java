package com.wipro.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.wipro.entities.Booking;

public interface BookingRepo extends JpaRepositoryImplementation<Booking, Long> {

}
