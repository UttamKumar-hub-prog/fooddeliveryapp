package com.wipro.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.wipro.entities.Payment;

public interface paymentrepo extends JpaRepositoryImplementation<Payment, Long> {

}
