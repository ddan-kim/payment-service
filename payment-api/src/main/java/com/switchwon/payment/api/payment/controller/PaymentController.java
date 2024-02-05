package com.switchwon.payment.api.payment.controller;

import com.switchwon.payment.api.account.dto.BalanceDTO;
import com.switchwon.payment.api.payment.dto.*;
import com.switchwon.payment.api.account.service.AccountService;
import com.switchwon.payment.api.payment.service.PaymentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
public class PaymentController {

    private final AccountService accountService;
    private final PaymentService paymentService;

    @GetMapping("balance/{id}")
    public ResponseEntity<BalanceDTO> balance(@PathVariable long id) {
        BalanceDTO response = accountService.findBalanceByUserId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("estimate")
    public ResponseEntity<PaymentEstimatedDTO> estimatePayment(@RequestBody PaymentEstimatedReqDTO paymentEstimatedReqDTO) {
        PaymentEstimatedDTO response = paymentService.estimatePayment(paymentEstimatedReqDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("approval")
    public ResponseEntity<PaymentDTO> approvalPayment(@RequestBody PaymentSaveDTO paymentSaveDTO) {
        PaymentDTO response = paymentService.approvePayment(paymentSaveDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

  }
