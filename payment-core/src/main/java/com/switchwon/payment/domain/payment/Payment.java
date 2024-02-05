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
@Table(name = "payment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Payment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long id;
	@Column(name = "payment_key", length = 50)
	private String paymentKey;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "currency", length = 3)
	private String currency;
	@Column(name = "merchant_id", length = 50)
	private String merchantId;
	@Column(name = "amount", precision = 15, scale = 2)
	private BigDecimal amount;
	@Column(name = "fees", precision = 15, scale = 2)
	private BigDecimal fees;
	@Column(name = "estimated_total_amount", precision = 15, scale = 2)
	private BigDecimal estimatedTotalAmount;
	@Column(name = "payment_method", length = 20)
	private String paymentMethod;
	@Column(name = "payment_status", length = 20)
	private String status;

	@Builder
	public Payment(Long id, String paymentKey, Long userId, String currency, String merchantId, BigDecimal amount,
		BigDecimal fees, BigDecimal estimatedTotalAmount, String paymentMethod, String status) {
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
	}
}