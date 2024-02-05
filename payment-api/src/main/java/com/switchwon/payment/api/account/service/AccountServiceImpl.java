package com.switchwon.payment.api.account.service;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.switchwon.payment.api.account.dto.BalanceDTO;
import com.switchwon.payment.api.account.repository.AccountRepository;
import com.switchwon.payment.domain.account.Account;
import com.switchwon.payment.error.exception.SwitchwonException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account findByUserId(Long userId) {
        Account account = accountRepository.findByUserId(userId);
        if (Objects.isNull(account)) {
            throw new SwitchwonException("계좌가 유효하지 않습니다.");
        }
        return account;
    }

    @Override
    public BalanceDTO findBalanceByUserId(Long userId) {
        Account account = this.findByUserId(userId);
        return BalanceDTO.createBalanceDTO(account);
    }

    @Override
    public void updateAccountBalance(Long userId, BigDecimal amount) {
        Account account = this.findByUserId(userId);

        // 결제금액이 잔금보다 클 경우
        if (amount.compareTo(account.getBalance()) > 0) {
            throw new SwitchwonException("잔액이 부족합니다.");
        }

        BigDecimal resultBalance = account.getBalance().subtract(amount);
        account.updateBalance(resultBalance);

        accountRepository.save(account);
    }

}
