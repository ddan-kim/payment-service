package com.switchwon.payment.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.switchwon.payment.api.account.service.AccountServiceImpl;
import com.switchwon.payment.api.payment.dto.PaymentEstimatedDTO;
import com.switchwon.payment.api.payment.dto.PaymentEstimatedReqDTO;
import com.switchwon.payment.api.payment.service.PaymentServiceImpl;
import com.switchwon.payment.domain.payment.PaymentFeeRate;
import com.switchwon.payment.error.exception.SwitchwonException;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTests {
	@InjectMocks
	private PaymentServiceImpl paymentService;
	@InjectMocks
	private AccountServiceImpl accountService;

	@Test
	void estimatePayment() {
		Long userId = 12345L;
		String currency = "USD";

		PaymentEstimatedReqDTO paymentEstimatedReqDTO = PaymentEstimatedReqDTO.builder()
			.userId(userId)
			.amount(new BigDecimal("150.00"))
			.currency(currency)
			.build();

		Double feeRate = PaymentFeeRate.getFeeRate(currency);
		BigDecimal amount = new BigDecimal("150.00");
		BigDecimal fees = amount.multiply(BigDecimal.valueOf(feeRate));
		BigDecimal estimatedTotal = amount.add(fees);

		PaymentEstimatedDTO paymentEstimatedDTO = PaymentEstimatedDTO.builder()
			.currency(currency)
			.estimatedTotal(estimatedTotal)
			.fees(fees)
			.build();

		when(paymentService.estimatePayment(paymentEstimatedReqDTO)).thenReturn(PaymentEstimatedDTO.builder()
			.estimatedTotal(estimatedTotal)
			.fees(fees)
			.currency("USD")
			.build());

		PaymentEstimatedDTO result = paymentService.estimatePayment(paymentEstimatedReqDTO);
		assertThat(result).isNotNull();
		assertThat(result.getFees()).isEqualByComparingTo(fees);
		assertThat(result.getCurrency()).isEqualTo(paymentEstimatedReqDTO.getCurrency());

	}

	@Test
	void estimatePaymentThrowsSwitchwonException() {
		Long userId = 999L;

		PaymentEstimatedReqDTO paymentEstimatedReqDTO = PaymentEstimatedReqDTO.builder()
			.userId(userId)
			.build();

		when(accountService.findByUserId(userId)).thenReturn(null);

		assertThrows(SwitchwonException.class, () -> paymentService.estimatePayment(paymentEstimatedReqDTO));
	}

}
