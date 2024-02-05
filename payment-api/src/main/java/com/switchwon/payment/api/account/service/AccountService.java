package com.switchwon.payment.api.account.service;

import java.math.BigDecimal;

import com.switchwon.payment.api.account.dto.BalanceDTO;
import com.switchwon.payment.domain.account.Account;

public interface AccountService {

    Account findByUserId(Long userId);

    BalanceDTO findBalanceByUserId(Long userId);

    void updateAccountBalance(Long userId, BigDecimal amount);
}
