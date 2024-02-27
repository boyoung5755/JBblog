package com.innovationT.blog.payment.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.innovationT.blog.Entity.Payment;
import com.innovationT.blog.payment.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
	
	private final PaymentRepository paymentRepository;

	@Override
	public void savePayment(Map<String, Object> payload) {
		
		Payment payment = new Payment();
		
		payment.setMemId("boyoung");
		payment.setApplyNum(String.valueOf(payload.get("applyNum")));
		payment.setImpUid(String.valueOf(payload.get("impUid")));
		payment.setMerchantUid(String.valueOf(payload.get("merchantUid")));
		payment.setPgProvider(String.valueOf(payload.get("pgProvider")));
		payment.setPgType(String.valueOf(payload.get("pgType")));
		payment.setProductName(String.valueOf(payload.get("productName")));
		payment.setPaidAmount(10);
		payment.setPaidDate(LocalDateTime.now());
		
		paymentRepository.save(payment);
		
	}

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

}
