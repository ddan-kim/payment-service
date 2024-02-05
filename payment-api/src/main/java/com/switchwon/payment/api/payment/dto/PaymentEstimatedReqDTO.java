package com.switchwon.payment.api.payment.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentEstimatedReqDTO {
    private BigDecimal amount;
    private String currency;
    private String destination;
    private Long userId;

    @Builder
    public PaymentEstimatedReqDTO(BigDecimal amount, String currency, String destination, Long userId) {
        this.amount = amount;
        this.currency = currency;
        this.destination = destination;
        this.userId = userId;
    }
}