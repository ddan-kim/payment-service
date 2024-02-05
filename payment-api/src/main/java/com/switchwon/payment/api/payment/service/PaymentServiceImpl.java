package com.switchwon.payment.api.payment.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.switchwon.payment.api.account.service.AccountService;
import com.switchwon.payment.api.payment.dto.PaymentDTO;
import com.switchwon.payment.api.payment.dto.PaymentDetailsSaveDTO;
import com.switchwon.payment.api.payment.dto.PaymentEstimatedDTO;
import com.switchwon.payment.api.payment.dto.PaymentEstimatedReqDTO;
import com.switchwon.payment.api.payment.dto.PaymentLogSaveDTO;
import com.switchwon.payment.api.payment.dto.PaymentSaveDTO;
import com.switchwon.payment.api.payment.repository.PaymentRepository;
import com.switchwon.payment.domain.account.Account;
import com.switchwon.payment.domain.payment.Payment;
import com.switchwon.payment.domain.payment.PaymentFeeRate;
import com.switchwon.payment.error.exception.SwitchwonException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final static String MID = "merchantId123";

	@Autowired
	private final AccountService accountService;
	private final PaymentLogService paymentLogService;
	private final PaymentDetailsService paymentDetailsService;
	private final PaymentRepository paymentRepository;

	@Override
	public PaymentEstimatedDTO estimatePayment(PaymentEstimatedReqDTO paymentEstimatedReqDTO) {

		Long userId = paymentEstimatedReqDTO.getUserId();
		Account account = accountService.findByUserId(userId);

		String currency = paymentEstimatedReqDTO.getCurrency();
		BigDecimal amount = paymentEstimatedReqDTO.getAmount();
		BigDecimal fees = this.calculateFees(amount, currency);
		BigDecimal estimatedTotal = amount.add(fees);

		// 결제금액이 잔금보다 클 경우
		if (amount.compareTo(account.getBalance()) > 0) {
			throw new SwitchwonException("잔액이 부족합니다.");
		}

		return PaymentEstimatedDTO.builder().estimatedTotal(estimatedTotal)
			.fees(fees)
			.currency(currency)
			.build();
	}

	/**
	 * <P> 결제 수수료 </P>
	 * @return fees (수수료)
	 */
	@Override
	public PaymentDTO approvePayment(PaymentSaveDTO paymentSaveDTO) {
		try {
			PaymentDTO paymentDTO = approve(paymentSaveDTO);
			paymentSaveDTO.setStatus(paymentDTO.getStatus());
			paymentSaveDTO.setPaymentKey(paymentDTO.getPaymentId());
			paymentSaveDTO.setFees(this.calculateFees(paymentSaveDTO.getAmount(), paymentSaveDTO.getCurrency()));
			paymentSaveDTO.setEstimatedTotalAmount(paymentSaveDTO.getAmount().add(paymentSaveDTO.getFees()));

			if (StringUtils.equals(paymentDTO.getStatus(), "approved")) {
				this.updateAccountBalance(paymentSaveDTO);
			}
			this.savePayment(paymentSaveDTO);
			return paymentDTO;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SwitchwonException("결제 승인에 실패했습니다.");
		}
	}

	/**
	 * <P> 결제 수수료 </P>
	 *
	 * @param amount 결제금액
	 * @param currency 통화
	 * @return fees (수수료)
	 */
	private BigDecimal calculateFees(BigDecimal amount, String currency) {
		Double feeRate = PaymentFeeRate.getFeeRate(currency);
		return amount.multiply(BigDecimal.valueOf(feeRate));
	}

	/**
	 * <P> 결제 승인 API 연동 </P>
	 *
	 * @return paymentDTO (결제 DTO)
	 */
	private PaymentDTO approve(PaymentSaveDTO paymentSaveDTO) {
		// 실제 결제 승인 API를 호출한다고 가정
		return PaymentDTO.builder()
			.paymentId("paymentId12345")
			.status("approved")
			.amount(paymentSaveDTO.getAmount())
			.currency(paymentSaveDTO.getCurrency())
			.timestamp(LocalDateTime.now())
			.build();
	}

	/**
	 * <P> 계좌 잔액 수정 </P>
	 * @param paymentSaveDTO 결제 saveDTO
	 */
	private void updateAccountBalance(PaymentSaveDTO paymentSaveDTO) {
		// 계좌잔액을 수정
		accountService.updateAccountBalance(paymentSaveDTO.getUserId(), paymentSaveDTO.getAmount());
		// 계좌내역을 저장
		// saveAccountTransaction(paymentReqDTO.getUserId(), "결제", paymentDTO.getAmount());
	}

	/**
	 * <P> 결제 정보 저장 </P>
	 * @param paymentSaveDTO 결제 saveDTO
	 */
	private void savePayment(PaymentSaveDTO paymentSaveDTO) {
		// 결제 정보 저장
		Payment payment = paymentRepository.save(paymentSaveDTO.toEntity());
		paymentSaveDTO.setId(payment.getId());
		PaymentDetailsSaveDTO paymentDetailsSaveDTO = paymentSaveDTO.getPaymentDetails();
		// 결제 상세 저장
		paymentDetailsService.save(PaymentDetailsSaveDTO.builder()
			.payment(payment)
			.cardNumber(paymentDetailsSaveDTO.getCardNumber())
			.expiryDate(paymentDetailsSaveDTO.getExpiryDate())
			.cvv(paymentDetailsSaveDTO.getCvv())
			.build());
		// 결제 log 저장
		paymentLogService.save(PaymentLogSaveDTO.of(paymentSaveDTO));
	}
}
