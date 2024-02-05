package com.switchwon.payment.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.switchwon.payment.api.account.dto.BalanceDTO;
import com.switchwon.payment.api.account.repository.AccountRepository;
import com.switchwon.payment.api.account.service.AccountServiceImpl;
import com.switchwon.payment.domain.account.Account;
import com.switchwon.payment.error.exception.SwitchwonException;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {

	@InjectMocks
	private AccountServiceImpl accountService;

	@Mock
	private AccountRepository accountRepository;

	@Test
	public void findBalanceByUserId() {
		// given
		Long userId = 12345L;
		Account account = Account.builder()
			.userId(userId)
			.balance(new BigDecimal("1000.00"))
			.currency("USD")
			.status("ACTIVE")
			.build();

		// when
		when(accountRepository.findByUserId(12345L)).thenReturn(account);
		BalanceDTO result = accountService.findBalanceByUserId(12345L);

		// then
		assertThat(result).isNotNull();
		assertThat(result.getUserId()).isEqualByComparingTo(account.getUserId());
		assertThat(result.getBalance()).isEqualByComparingTo(account.getBalance());
		assertThat(result.getCurrency()).isEqualTo(account.getCurrency());

	}

	@Test
	public void updateAccountBalance() {
		// given
		Long userId = 12345L;
		BigDecimal balance = new BigDecimal("1000.00");
		Account account = Account.builder()
			.userId(userId)
			.balance(balance)
			.currency("USD")
			.status("ACTIVE")
			.build();

		// when
		when(accountRepository.findByUserId(userId)).thenReturn(account);
		BigDecimal paymentAmount = new BigDecimal("500.00");
		// accountService.updateAccountBalance(userId, paymentAmount);

		// then
		assertDoesNotThrow(() -> accountService.updateAccountBalance(userId, paymentAmount));
		assertEquals(balance.subtract(paymentAmount), account.getBalance());
		verify(accountRepository, times(1)).save(account);
	}

	@Test
	public void testUpdateAccountBalanceSufficientFunds() {
		// Arrange
		Long userId = 1L;
		BigDecimal initialBalance = BigDecimal.valueOf(1000.00);
		BigDecimal amount = BigDecimal.valueOf(500.00);
		Account account = Account.builder()
			.userId(userId)
			.balance(initialBalance)
			.currency("USD")
			.status("ACTIVE")
			.build();
		when(accountRepository.findByUserId(userId)).thenReturn(account);

		// Act
		assertDoesNotThrow(() -> accountService.updateAccountBalance(userId, amount));

		// Assert
		assertEquals(initialBalance.subtract(amount), account.getBalance());
		verify(accountRepository, times(1)).save(account);
	}

	@Test
	public void testUpdateAccountBalanceInsufficientFunds() {
		// Arrange
		Long userId = 1L;
		BigDecimal initialBalance = BigDecimal.valueOf(1000.00);
		BigDecimal amount = BigDecimal.valueOf(1500.00);
		Account account = Account.builder()
			.userId(userId)
			.balance(initialBalance)
			.currency("USD")
			.status("ACTIVE")
			.build();
		Mockito.when(accountRepository.findByUserId(userId)).thenReturn(account);

		// Act & Assert
		SwitchwonException exception = assertThrows(SwitchwonException.class,
			() -> accountService.updateAccountBalance(userId, amount));

		assertEquals("잔액이 부족합니다.", exception.getMessage());
		assertEquals(initialBalance, account.getBalance());
		Mockito.verify(accountRepository, Mockito.never()).save(account);
	}

	@Test
	public void testUpdateAccountBalanceUserNotFound() {
		// Arrange
		Long userId = 12345L;

		Mockito.when(accountRepository.findByUserId(userId)).thenReturn(null);

		// Act & Assert
		SwitchwonException exception = assertThrows(SwitchwonException.class,
			() -> accountService.updateAccountBalance(userId, BigDecimal.valueOf(500.00)));

		assertEquals("계좌가 유효하지 않습니다.", exception.getMessage());
		Mockito.verify(accountRepository, Mockito.never()).save(Mockito.any(Account.class));
	}

}
