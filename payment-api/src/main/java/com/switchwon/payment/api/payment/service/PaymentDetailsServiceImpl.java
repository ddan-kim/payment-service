package com.switchwon.payment.api.payment.service;

import org.springframework.stereotype.Service;

import com.switchwon.payment.api.payment.dto.PaymentDetailsSaveDTO;
import com.switchwon.payment.api.payment.repository.PaymentDetailisRepository;
import com.switchwon.payment.api.payment.repository.PaymentLogRepository;
import com.switchwon.payment.domain.payment.PaymentDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentDetailsServiceImpl implements PaymentDetailsService {
	private final PaymentDetailisRepository paymentDetailisRepository;

	@Override
	public PaymentDetails save(PaymentDetailsSaveDTO paymentDetailsSaveDTO) {
		return paymentDetailisRepository.save(paymentDetailsSaveDTO.toEntity());
	}
}
