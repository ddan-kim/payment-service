package com.switchwon.payment.api.payment.dto;

import java.math.BigDecimal;
import java.util.Objects;

import com.switchwon.payment.domain.payment.PaymentLog;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentLogSaveDTO {
	private Long paymentLogId;
	private Long paymentId;
	private String paymentKey;
	private Long userId;
	private String currency;
	private String merchantId;
	private BigDecimal amount;
	private BigDecimal fees;
	private BigDecimal estimatedTotalAmount;
	private String paymentMethod;
	private String paymentStatus;
	private String cardNumber;
	private String expiryDate;
	private String cvv;

	@Builder
	public PaymentLogSaveDTO(Long paymentLogId, Long paymentId, String paymentKey, Long userId, String currency,
		String merchantId, BigDecimal amount, BigDecimal fees, BigDecimal estimatedTotalAmount,
		String paymentMethod, String paymentStatus, String cardNumber, String expiryDate, String cvv) {
		this.paymentLogId = paymentLogId;
		this.paymentId = paymentId;
		this.paymentKey = paymentKey;
		this.userId = userId;
		this.currency = currency;
		this.merchantId = merchantId;
		this.amount = amount;
		this.fees = fees;
		this.estimatedTotalAmount = estimatedTotalAmount;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
	}

	public PaymentLog toEntity() {
		return PaymentLog.builder()
			.paymentId(paymentId)
			.paymentKey(paymentKey)
			.userId(userId)
			.currency(currency)
			.merchantId(merchantId)
			.amount(amount)
			.fees(fees)
			.estimatedTotalAmount(estimatedTotalAmount)
			.paymentMethod(paymentMethod)
			.paymentStatus(paymentStatus)
			.cardNumber(cardNumber)
			.expiryDate(expiryDate)
			.cvv(cvv)
			.build();
	}

	public static PaymentLogSaveDTO of(PaymentSaveDTO paymentSaveDTO) {
		PaymentDetailsSaveDTO paymentDetailsSaveDTO = paymentSaveDTO.getPaymentDetails();

		return PaymentLogSaveDTO.builder()
			.paymentId(paymentSaveDTO.getId())
			.paymentKey(paymentSaveDTO.getPaymentKey())
			.userId(paymentSaveDTO.getUserId())
			.currency(paymentSaveDTO.getCurrency())
			.merchantId(paymentSaveDTO.getMerchantId())
			.amount(paymentSaveDTO.getAmount())
			.fees(paymentSaveDTO.getFees())
			.estimatedTotalAmount(paymentSaveDTO.getEstimatedTotalAmount())
			.paymentMethod(paymentSaveDTO.getPaymentMethod())
			.paymentStatus(paymentSaveDTO.getStatus())
			.cardNumber(Objects.isNull(paymentDetailsSaveDTO) ? null : paymentDetailsSaveDTO.getCardNumber())
			.expiryDate(Objects.isNull(paymentDetailsSaveDTO) ? null : paymentDetailsSaveDTO.getExpiryDate())
			.cvv(Objects.isNull(paymentDetailsSaveDTO) ? null : paymentDetailsSaveDTO.getCvv())
			.build();
	}
}
