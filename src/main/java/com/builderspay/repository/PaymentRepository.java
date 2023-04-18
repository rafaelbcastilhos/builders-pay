package com.builderspay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.builderspay.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}
