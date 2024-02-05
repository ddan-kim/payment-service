package com.switchwon.payment.api.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.switchwon.payment.domain.payment.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
