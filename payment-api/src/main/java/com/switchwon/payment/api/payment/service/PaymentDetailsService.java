package com.switchwon.payment.api.payment.service;

import com.switchwon.payment.api.payment.dto.PaymentDetailsSaveDTO;
import com.switchwon.payment.domain.payment.PaymentDetails;

public interface PaymentDetailsService {
	PaymentDetails save(PaymentDetailsSaveDTO paymentDetailsSaveDTO);
}
