package com.switchwon.payment.api.account.dto;

import java.math.BigDecimal;

import com.switchwon.payment.domain.account.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BalanceDTO {
    private Long userId;
    private BigDecimal balance;
    private String currency;

    @Builder
    public BalanceDTO(Long userId, BigDecimal balance, String currency) {
        this.userId = userId;
        this.balance = balance;
        this.currency = currency;
    }

    public static BalanceDTO createBalanceDTO(Account account) {
        return BalanceDTO.builder().userId(account.getUserId()).balance(account.getBalance()).currency(
            account.getCurrency()).build();
    }

}
