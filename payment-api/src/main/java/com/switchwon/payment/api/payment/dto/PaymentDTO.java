package com.switchwon.payment.api.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentDTO {
	private String paymentId;
	private String status;
	private BigDecimal amount;
	private String currency;
	private LocalDateTime timestamp;

	@Builder
	public PaymentDTO(String paymentId, String status, BigDecimal amount, String currency,
		LocalDateTime timestamp) {
		this.paymentId = paymentId;
		this.status = status;
		this.amount = amount;
		this.currency = currency;
		this.timestamp = timestamp;
	}
}
