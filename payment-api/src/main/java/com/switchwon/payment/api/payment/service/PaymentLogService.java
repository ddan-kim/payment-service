package com.switchwon.payment.api.payment.service;

import com.switchwon.payment.api.payment.dto.PaymentLogSaveDTO;
import com.switchwon.payment.api.payment.dto.PaymentSaveDTO;
import com.switchwon.payment.domain.payment.PaymentLog;

public interface PaymentLogService {
	PaymentLog save(PaymentLogSaveDTO paymentLogSaveDTO);
}
