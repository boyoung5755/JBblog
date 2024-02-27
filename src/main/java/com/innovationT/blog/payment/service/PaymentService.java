package com.innovationT.blog.payment.service;

import java.util.Map;

public interface PaymentService {

	/**결제내역 등록
	 * @param payment
	 */
	public void savePayment(Map<String, Object> payload);

}
