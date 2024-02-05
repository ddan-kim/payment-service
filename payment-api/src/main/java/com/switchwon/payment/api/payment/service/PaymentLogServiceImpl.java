package com.switchwon.payment.api.payment.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.switchwon.payment.api.payment.dto.PaymentLogSaveDTO;
import com.switchwon.payment.api.payment.dto.PaymentSaveDTO;
import com.switchwon.payment.api.payment.repository.PaymentLogRepository;
import com.switchwon.payment.domain.payment.PaymentLog;
import com.switchwon.payment.error.exception.SwitchwonException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentLogServiceImpl implements PaymentLogService {
	private final PaymentLogRepository paymentLogRepository;

	@Override
	@Transactional
	public PaymentLog save(PaymentLogSaveDTO paymentLogSaveDTO) {
		return paymentLogRepository.save(paymentLogSaveDTO.toEntity());
	}
}
