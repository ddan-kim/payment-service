package com.switchwon.payment.api.payment.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentEstimatedDTO {
    private BigDecimal estimatedTotal;
    private BigDecimal fees;
    private String currency;

    @Builder
    public PaymentEstimatedDTO(BigDecimal estimatedTotal, BigDecimal fees, String currency) {
        this.estimatedTotal = estimatedTotal;
        this.fees = fees;
        this.currency = currency;
    }

}