package com.switchwon.payment.api.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentDetailsDTO {
	private Long paymentId;
	private String cardNumber;
	private String expiryDate;
	private String cvv;

	@Builder
	public PaymentDetailsDTO(Long paymentId, String cardNumber, String expiryDate, String cvv) {
		this.paymentId = paymentId;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
	}
}