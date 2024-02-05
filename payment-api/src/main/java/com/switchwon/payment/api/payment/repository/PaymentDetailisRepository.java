package com.switchwon.payment.api.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.switchwon.payment.domain.payment.PaymentDetails;

public interface PaymentDetailisRepository extends JpaRepository<PaymentDetails, Long> {
}
