package com.switchwon.payment.api.payment.service;

import com.switchwon.payment.api.payment.dto.PaymentDTO;
import com.switchwon.payment.api.payment.dto.PaymentEstimatedDTO;
import com.switchwon.payment.api.payment.dto.PaymentEstimatedReqDTO;
import com.switchwon.payment.api.payment.dto.PaymentSaveDTO;

public interface PaymentService {

	/**
	 * <P> 결제 수수료 </P>
	 *
	 * @param paymentEstimatedReqDTO 결제 예상 Req
	 * @return PaymentEstimatedDTO 결제 예상 결과 DTO
	 */
	PaymentEstimatedDTO estimatePayment(PaymentEstimatedReqDTO paymentEstimatedReqDTO);

	/**
	 * <P> 결제 수수료 </P>
	 *
	 * @param paymentSaveDTO 결제 저장 DTO
	 * @return PaymentDTO 결제 승인 결과 DTO
	 */
	PaymentDTO approvePayment(PaymentSaveDTO paymentSaveDTO);


}
