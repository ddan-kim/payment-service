package com.switchwon.payment.domain.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_details")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PaymentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_detail_id")
	private Long id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
	private Payment payment;
	@Column(name = "card_number", length = 50)
	private String cardNumber;
	@Column(name = "expiry_date", length = 7)
	private String expiryDate;
	@Column(name = "cvv", length = 3)
	private String cvv;

	@Builder
	public PaymentDetails(Long id, Payment payment, String cardNumber, String expiryDate, String cvv) {
		this.id = id;
		this.payment = payment;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
	}

}