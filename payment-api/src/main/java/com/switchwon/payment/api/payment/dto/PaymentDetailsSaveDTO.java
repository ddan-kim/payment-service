package com.switchwon.payment.api.payment.dto;

import com.switchwon.payment.domain.payment.Payment;
import com.switchwon.payment.domain.payment.PaymentDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDetailsSaveDTO {

	private Long id;
	private Payment payment;
	private String cardNumber;
	private String expiryDate;
	private String cvv;

	@Builder
	public PaymentDetailsSaveDTO(Payment payment, String cardNumber, String expiryDate, String cvv) {
		this.payment = payment;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
	}

	public PaymentDetails toEntity() {
		return PaymentDetails.builder()
			.payment(payment)
			.cardNumber(cardNumber)
			.expiryDate(expiryDate)
			.cvv(cvv)
			.build();
	}

}
