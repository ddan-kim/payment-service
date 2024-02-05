package com.switchwon.payment.api.payment.dto;

import java.math.BigDecimal;

import com.switchwon.payment.domain.payment.Payment;
import com.switchwon.payment.domain.payment.PaymentLog;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentSaveDTO {

	private Long id;
	private String paymentKey;
	private Long userId;
	private String currency;
	private String merchantId;
	private BigDecimal amount;
	private BigDecimal fees;
	private BigDecimal estimatedTotalAmount;
	private String paymentMethod;
	private String status;
	private PaymentDetailsSaveDTO paymentDetails;

	@Builder
	public PaymentSaveDTO(Long id, String paymentKey, Long userId, String currency, String merchantId,
		BigDecimal amount,
		BigDecimal fees, BigDecimal estimatedTotalAmount, String paymentMethod, String status,
		PaymentDetailsSaveDTO paymentDetailsSaveDTO) {
		this.id = id;
		this.paymentKey = paymentKey;
		this.userId = userId;
		this.currency = currency;
		this.merchantId = merchantId;
		this.amount = amount;
		this.fees = fees;
		this.estimatedTotalAmount = estimatedTotalAmount;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.paymentDetails = paymentDetailsSaveDTO;
	}

	public Payment toEntity() {
		return Payment.builder()
			.paymentKey(paymentKey)
			.userId(userId)
			.currency(currency)
			.merchantId(merchantId)
			.amount(amount)
			.fees(fees)
			.estimatedTotalAmount(estimatedTotalAmount)
			.paymentMethod(paymentMethod)
			.status(status)
			.build();
	}

}
