package com.switchwon.payment.domain.payment;

import java.math.BigDecimal;

import com.switchwon.payment.domain.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_log")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PaymentLog extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_log_id")
	private Long paymentLogId;
	@Column(name = "payment_id")
	private Long paymentId;
	@Column(name = "payment_key")
	private String paymentKey;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "currency")
	private String currency;
	@Column(name = "merchant_id")
	private String merchantId;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "fees")
	private BigDecimal fees;
	@Column(name = "estimated_total_amount")
	private BigDecimal estimatedTotalAmount;
	@Column(name = "balance")
	private BigDecimal balance;
	@Column(name = "payment_method")
	private String paymentMethod;
	@Column(name = "payment_status")
	private String paymentStatus;
	@Column(name = "card_number")
	private String cardNumber;
	@Column(name = "expiry_date")
	private String expiryDate;
	@Column(name = "cvv")
	private String cvv;

	@Builder
	public PaymentLog(Long paymentLogId, Long paymentId, String paymentKey, Long userId, String currency,
		String merchantId,
		BigDecimal amount, BigDecimal fees, BigDecimal estimatedTotalAmount, BigDecimal balance, String paymentMethod,
		String paymentStatus, String cardNumber, String expiryDate, String cvv) {
		this.paymentLogId = paymentLogId;
		this.paymentId = paymentId;
		this.paymentKey = paymentKey;
		this.userId = userId;
		this.currency = currency;
		this.merchantId = merchantId;
		this.amount = amount;
		this.fees = fees;
		this.estimatedTotalAmount = estimatedTotalAmount;
		this.balance = balance;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
	}
}