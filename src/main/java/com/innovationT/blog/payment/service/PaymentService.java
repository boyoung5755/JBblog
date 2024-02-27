package com.innovationT.blog.payment.service;

import java.util.List;
import java.util.Map;

import com.innovationT.blog.Entity.Payment;

public interface PaymentService {

	/**
	 * 결제내역 등록
	 * @param payment
	 */
	public void savePayment(Map<String, Object> payload);

	/**
	 * 결제내역 목록
	 * @return
	 */
	public List<Payment> getAllPayments();

}
