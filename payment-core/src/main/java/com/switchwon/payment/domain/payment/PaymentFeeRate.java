package com.switchwon.payment.domain.payment;

public enum PaymentFeeRate {

	USD(0.015, "USD"),
	JPY(0.015, "JPY"),
	EUR(0.015, "EUR"),
	DE(0.003, null);

	private Double feeRate;
	private String currency;

	PaymentFeeRate(Double feeRate, String currency) {
		this.feeRate = feeRate;
		this.currency = currency;
	}

	public static Double getFeeRate(String currency) {
		if (USD.currency.equals(currency)) {
			return USD.getValue();
		} else if (JPY.currency.equals(currency)) {
			return JPY.getValue();
		} else {
			return DE.getValue();
		}
	}

	public Double getValue() {
		return this.feeRate;
	}

}
