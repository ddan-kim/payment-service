package com.switchwon.payment.domain.account;

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
@Table(name = "account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "account_number", unique = true, nullable = false, length = 50)
	private String accountNumber;

	@Column(name = "account_type", nullable = false, length = 20)
	private String accountType;

	@Column(name = "currency", nullable = false, length = 3)
	private String currency;

	@Column(name = "balance")
	private BigDecimal balance;

	@Column(name = "account_status", nullable = false, length = 10)
	private String status;

	@Builder
	public Account(Long accountId, Long userId, String accountNumber, String accountType, String currency,
		BigDecimal balance, String status) {
		this.accountId = accountId;
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.currency = currency;
		this.balance = balance;
		this.status = status;
	}

	public Account updateBalance(BigDecimal balance) {
		this.balance = balance;
		return this;
	}
}
